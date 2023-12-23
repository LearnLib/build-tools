/* Copyright (C) 2023 TU Dortmund University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.learnlib.tooling.processor.edsl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import com.github.misberner.apcommons.util.ElementUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import com.squareup.javapoet.TypeVariableName;
import de.learnlib.tooling.annotation.edsl.Action;
import de.learnlib.tooling.annotation.edsl.Expr;
import de.learnlib.tooling.annotation.edsl.GenerateEDSL;
import de.learnlib.tooling.processor.AbstractLearnLibProcessor;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;
import org.kohsuke.MetaInfServices;

@MetaInfServices(Processor.class)
public class EDSLProcessor extends AbstractLearnLibProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<>(Arrays.asList(GenerateEDSL.class.getName(), Action.class.getName()));
    }

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        // set this property so that the BRICS automaton has a deterministic state iteration order
        System.setProperty("dk.brics.automaton.debug", "true");
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        for (Element e : roundEnv.getElementsAnnotatedWith(GenerateEDSL.class)) {

            final TypeElement elem = (TypeElement) e;
            final GenerateEDSL annotation = elem.getAnnotation(GenerateEDSL.class);

            final String name = annotation.name();
            final String pkg = getPackageName(elem, annotation);
            final Modifier[] modifiers = annotation.isPublic() ? new Modifier[] {Modifier.PUBLIC} : new Modifier[0];
            final String syntax = getExpandedSyntax(annotation);
            final List<String> tokens = getTokens(syntax);

            final Map<String, Character> token2Label = new HashMap<>();
            final Automaton automaton = getAutomaton(syntax, tokens, token2Label);
            final Map<String, List<ExecutableElement>> token2Method = getDomainActionMap(elem, tokens);
            final Map<State, TypeSpec.Builder> state2Builder = new LinkedHashMap<>(); // ensure deterministic output
            final Map<State, MethodSpec> state2getter = new HashMap<>();

            final List<ExecutableElement> constructors = getAnnotatedConstructors(elem);

            // generate base structure
            final ClassName targetName = ClassName.get(pkg, name);
            final TypeSpec.Builder targetBuilder = createBuilder(elem, annotation, targetName);
            final TypeName sourceType = ParameterizedTypeName.get(elem.asType());
            final FieldSpec delegate =
                    FieldSpec.builder(sourceType, "delegate", Modifier.PRIVATE, Modifier.FINAL).build();
            targetBuilder.addField(delegate);

            for (ExecutableElement c : constructors) {
                final MethodSpec.Builder cBuilder = MethodSpec.constructorBuilder().addModifiers(modifiers);
                final StringJoiner sj = new StringJoiner(", ", "$N = new $T(", ")");
                for (VariableElement p : c.getParameters()) {
                    String pName = p.getSimpleName().toString();
                    cBuilder.addParameter(ParameterizedTypeName.get(p.asType()), pName);
                    sj.add(pName);
                }
                cBuilder.addStatement(CodeBlock.of(sj.toString(), delegate, sourceType));
                targetBuilder.addMethod(cBuilder.build());
            }

            // generate state classes
            int id = 0;
            for (State s : automaton.getStates()) {
                // the initial state is represented by the class itself
                final boolean isInit = Objects.equals(s, automaton.getInitialState());
                final Builder cBuilder;
                final ClassName cName;
                final TypeName cType;

                if (isInit) {
                    cBuilder = targetBuilder;
                    cName = targetName;
                    cType = targetBuilder.typeVariables.isEmpty() ?
                            targetName :
                            ParameterizedTypeName.get(targetName, targetBuilder.typeVariables.toArray(new TypeName[0]));
                } else {
                    cName = targetName.nestedClass(name + id++);
                    cType = cName;
                    cBuilder = TypeSpec.classBuilder(cName)
                                       .addModifiers(modifiers)
                                       .addModifiers(Modifier.FINAL)
                                       .addMethod(MethodSpec.constructorBuilder()
                                                            .addModifiers(Modifier.PRIVATE)
                                                            .build());
                }

                final MethodSpec.Builder mBuilder = MethodSpec.methodBuilder("get" + cName.simpleName())
                                                              .addModifiers(Modifier.PRIVATE)
                                                              .returns(cType);

                if (isInit) {
                    mBuilder.addStatement("return this");
                } else {
                    final FieldSpec fSpec = FieldSpec.builder(cType, cName.simpleName(), Modifier.PRIVATE).build();
                    targetBuilder.addField(fSpec);
                    mBuilder.beginControlFlow("if ($N == null)", fSpec)
                            .addStatement("$N = new $T()", fSpec, cType)
                            .endControlFlow()
                            .addStatement("return $N", fSpec);
                }

                final MethodSpec mSpec = mBuilder.build();
                targetBuilder.addMethod(mSpec);

                state2Builder.put(s, cBuilder);
                state2getter.put(s, mSpec);
            }

            // generate adjacency
            for (State state : automaton.getStates()) {
                final Builder builder = state2Builder.get(state);
                for (Entry<String, Character> entry : token2Label.entrySet()) {
                    final String token = entry.getKey();
                    final Character label = entry.getValue();
                    final State succ = state.step(label);

                    if (succ != null) {
                        final MethodSpec getter = state2getter.get(succ);
                        for (ExecutableElement m : token2Method.getOrDefault(token, Collections.emptyList())) {
                            final MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(token)
                                                                               .addModifiers(modifiers)
                                                                               .varargs(m.isVarArgs())
                                                                               .addExceptions(m.getThrownTypes()
                                                                                               .stream()
                                                                                               .map(TypeName::get)
                                                                                               .collect(Collectors.toList()));

                            final StringJoiner sj = new StringJoiner(", ", "$N.$N(", ")");
                            VariableElement lastParameter = null;
                            for (VariableElement p : m.getParameters()) {
                                String pName = p.getSimpleName().toString();
                                methodBuilder.addParameter(ParameterSpec.builder(ParameterizedTypeName.get(p.asType()),
                                                                                 pName).build());
                                sj.add(pName);
                                lastParameter = p;
                            }

                            final boolean isSafeVarargs = lastParameter != null && m.isVarArgs() &&
                                                          ((ArrayType) lastParameter.asType()).getComponentType()
                                                                                              .getKind() ==
                                                          TypeKind.TYPEVAR;
                            if (isSafeVarargs) {
                                methodBuilder.addModifiers(Modifier.FINAL).addAnnotation(SafeVarargs.class);
                            }

                            final CodeBlock callDelegate = CodeBlock.of(sj.toString(), delegate, m.getSimpleName());
                            final Action ann = m.getAnnotation(Action.class);
                            if (ann != null && ann.isTerminating()) {
                                if (succ.isAccept()) {
                                    final CodeBlock statement =
                                            CodeBlock.builder().add(CodeBlock.of("return ")).add(callDelegate).build();
                                    methodBuilder.addStatement(statement)
                                                 .returns(ParameterizedTypeName.get(m.getReturnType()));
                                    builder.addMethod(methodBuilder.build());
                                }
                            } else {
                                methodBuilder.addStatement(callDelegate)
                                             .addStatement("return $N()", getter)
                                             .returns(getter.returnType);
                                builder.addMethod(methodBuilder.build());
                            }
                        }
                    }
                }
            }

            // combine everything
            for (Entry<State, Builder> entry : state2Builder.entrySet()) {
                if (!entry.getKey().equals(automaton.getInitialState())) {
                    targetBuilder.addType(entry.getValue().build());
                }
            }

            try {
                JavaFile.builder(pkg, targetBuilder.build())
                        .indent("    ")
                        .build()
                        .writeTo(super.processingEnv.getFiler());
            } catch (IOException ioe) {
                super.error("Could not writer source: " + ioe.getMessage());
            }
        }
        return true;
    }

    private String getExpandedSyntax(GenerateEDSL annotation) {

        String result = annotation.syntax();

        for (Expr expr : annotation.where()) {
            final String name = expr.name();
            final String syntax = expr.syntax();

            result = result.replace("<" + name + ">", syntax);
        }

        if (result.contains("<") || result.contains(">")) {
            throw new IllegalArgumentException(
                    '\'' + annotation.syntax() + "' contains expressions that could not be substituted");
        }

        return result;
    }

    private List<String> getTokens(String syntax) {
        return Arrays.stream(syntax.split("[\\(\\)\\*\\+\\|\\? ]"))
                     .filter(t -> !t.isEmpty())
                     .distinct()
                     .sorted(Comparator.comparing(String::length).reversed())
                     .collect(Collectors.toList());
    }

    private Automaton getAutomaton(String syntax, List<String> tokens, Map<String, Character> token2label) {
        String regexp = syntax;
        char idx = Character.MIN_VALUE; // use 'a' for debugging
        for (String t : tokens) {
            regexp = regexp.replace(t, Character.toString(idx));
            token2label.put(t, idx);
            idx++;
        }
        regexp = regexp.replace(" ", "");

        final RegExp regExp = new RegExp(regexp, RegExp.INTERSECTION | RegExp.COMPLEMENT);
        final Automaton automaton = regExp.toAutomaton(true);
        assert automaton.isDeterministic();

        return automaton;
    }

    private Map<String, List<ExecutableElement>> getDomainActionMap(TypeElement clazz, List<String> token) {

        final Set<String> tokensAsSet = new HashSet<>(token);
        final Map<String, List<ExecutableElement>> result = new HashMap<>();

        for (ExecutableElement m : ElementFilter.methodsIn(getAnnotatedElements(clazz))) {
            final String name = m.getSimpleName().toString();
            if (tokensAsSet.contains(name)) {
                result.computeIfAbsent(name, n -> new ArrayList<>()).add(m);
            }
        }

        if (!result.keySet().containsAll(tokensAsSet)) {
            tokensAsSet.removeAll(result.keySet());
            throw new IllegalArgumentException("Could not find action for tokens: " + tokensAsSet);
        }

        return result;
    }

    private List<Element> getAnnotatedElements(TypeElement clazz) {
        return super.processingEnv.getElementUtils()
                                  .getAllMembers(clazz)
                                  .stream()
                                  .filter(e -> e.getAnnotation(Action.class) != null)
                                  .collect(Collectors.toList());
    }

    private List<ExecutableElement> getAnnotatedConstructors(TypeElement clazz) {
        final List<ExecutableElement> constructors = ElementFilter.constructorsIn(getAnnotatedElements(clazz));

        if (constructors.isEmpty()) {
            throw new IllegalArgumentException("Could not find annotated constructor");
        }

        return constructors;
    }

    private TypeSpec.Builder createBuilder(Element element, GenerateEDSL generateEDSL, ClassName name) {
        final TypeSpec.Builder builder = TypeSpec.classBuilder(name)
                                                 .addJavadoc(
                                                         "This is an auto-generated embedded domain-specific language for {@link $T}.\n",
                                                         super.processingEnv.getTypeUtils().erasure(element.asType()))
                                                 .addAnnotation(super.createAnnotation(element));

        final DeclaredType clazz = (DeclaredType) element.asType();

        for (TypeMirror ta : clazz.getTypeArguments()) {
            TypeVariableName typeName = (TypeVariableName) ParameterizedTypeName.get(ta);
            builder.addTypeVariable(typeName);
        }

        if (generateEDSL.isPublic()) {
            builder.addModifiers(Modifier.PUBLIC);
        }

        return builder;
    }

    private String getPackageName(Element element, GenerateEDSL annotation) {
        final String pkg = annotation.packageName();

        if (pkg == null || pkg.isEmpty()) {
            return ElementUtils.getPackageName(element);
        } else {
            return pkg;
        }
    }
}
