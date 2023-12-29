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
import java.util.Collection;
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
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

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

            // cast is fine because annotation is only allowed on ElementType.TYPE
            final TypeElement elem = (TypeElement) e;
            final boolean isClass = elem.getKind() == ElementKind.CLASS;
            final GenerateEDSL annotation = elem.getAnnotation(GenerateEDSL.class);

            final String syntax = getExpandedSyntax(elem, annotation);
            if (syntax == null) {
                continue;
            }

            final String name = annotation.name();
            final String pkg = super.getPackageName(elem, annotation.packageName());
            final Modifier[] modifiers = annotation.isPublic() ? new Modifier[] {Modifier.PUBLIC} : new Modifier[0];

            final List<String> tokens = getTokens(syntax);
            final Map<String, Character> token2Label = new HashMap<>();
            final Automaton automaton = getAutomaton(syntax, tokens, token2Label);
            final Map<String, List<ExecutableElement>> token2Method = getDomainActionMap(elem, tokens, annotation);

            if (token2Method == null) {
                continue;
            }

            final Collection<State> relevantStates =
                    filterIrrelevantStates(automaton, tokens, token2Label, token2Method);
            final Map<State, TypeSpec.Builder> state2Builder = new LinkedHashMap<>(); // ensure deterministic output
            final Map<State, MethodSpec> state2getter = new HashMap<>();

            // generate base structure
            final ClassName targetName = ClassName.get(pkg, name);
            final TypeSpec.Builder targetBuilder = createBuilder(elem, annotation, targetName);
            final TypeName sourceType = ParameterizedTypeName.get(elem.asType());
            final FieldSpec delegate =
                    FieldSpec.builder(sourceType, "delegate", Modifier.PRIVATE, Modifier.FINAL).build();
            targetBuilder.addField(delegate);

            final List<ExecutableElement> constructors = ElementFilter.constructorsIn(getAnnotatedElements(elem));

            if (isClass && !constructors.isEmpty()) {
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
            } else {
                final String orig = "orig";
                targetBuilder.addMethod(MethodSpec.constructorBuilder()
                                                  .addModifiers(modifiers)
                                                  .addParameter(sourceType, orig)
                                                  .addStatement("$N = $N", delegate, orig)
                                                  .build());
            }

            // generate state classes
            int id = 0;
            for (State s : relevantStates) {
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
            for (State state : relevantStates) {
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
                            for (VariableElement p : m.getParameters()) {
                                String pName = p.getSimpleName().toString();
                                methodBuilder.addParameter(ParameterSpec.builder(ParameterizedTypeName.get(p.asType()),
                                                                                 pName).build());
                                sj.add(pName);
                            }

                            if (m.isVarArgs() && super.requiresSafeVarargs(methodBuilder)) {
                                methodBuilder.addModifiers(Modifier.FINAL).addAnnotation(SafeVarargs.class);
                            }

                            final CodeBlock callDelegate = CodeBlock.of(sj.toString(), delegate, m.getSimpleName());
                            final Action ann = m.getAnnotation(Action.class);
                            if (ann != null && ann.isTerminating() && succ.isAccept()) {
                                final CodeBlock.Builder sBuilder = CodeBlock.builder();
                                if (m.getReturnType().getKind() != TypeKind.VOID) {
                                    sBuilder.add(CodeBlock.of("return "));
                                }
                                sBuilder.add(callDelegate);
                                methodBuilder.addStatement(sBuilder.build())
                                             .returns(ParameterizedTypeName.get(m.getReturnType()));
                                builder.addMethod(methodBuilder.build());
                            } else {
                                if (ann != null && ann.isTerminating()) {
                                    super.printWarning(
                                            "The annotated method is marked as terminating but does not terminate the regular expression. Treating it as non-terminating.",
                                            m,
                                            ann);
                                }
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

            final JavaFile file = JavaFile.builder(pkg, targetBuilder.build()).indent("    ").build();

            try {
                file.writeTo(super.processingEnv.getFiler());
            } catch (IOException ioe) {
                super.printError("Could not write file: " + ioe.getMessage(), elem);
            }
        }
        return true;
    }

    private String getExpandedSyntax(TypeElement clazz, GenerateEDSL annotation) {

        String result = annotation.syntax();

        for (Expr expr : annotation.where()) {
            final String name = expr.name();
            final String syntax = expr.syntax();

            result = result.replace("<" + name + ">", syntax);
        }

        if (result.contains("<") || result.contains(">")) {
            super.printError("The specified syntax contains expressions that could not be substituted",
                             clazz,
                             annotation);
            return null;
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

    /*
     * A state is relevant if it is either
     * - the initial state
     * - a non-accepting state
     * - has an incoming, non-terminating transition
     */
    private Collection<State> filterIrrelevantStates(Automaton automaton,
                                                     Collection<String> tokens,
                                                     Map<String, Character> token2labels,
                                                     Map<String, List<ExecutableElement>> token2Methods) {
        final Set<State> states = automaton.getStates();
        final List<State> result = new ArrayList<>(states.size());

        states:
        for (State state : states) {
            if (Objects.equals(state, automaton.getInitialState())) {
                result.add(state);
            } else if (!state.isAccept()) {
                result.add(state);
            } else {
                for (String token : tokens) {
                    for (ExecutableElement m : token2Methods.getOrDefault(token, Collections.emptyList())) {
                        final Action ann = m.getAnnotation(Action.class);
                        if (ann != null && !ann.isTerminating()) {
                            final Character label = token2labels.get(token);
                            assert label != null;
                            for (State s : states) {
                                if (state.equals(s.step(label))) {
                                    result.add(state);
                                    continue states;
                                }
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    private Map<String, List<ExecutableElement>> getDomainActionMap(TypeElement clazz,
                                                                    List<String> token,
                                                                    GenerateEDSL annotation) {

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
            super.printError("Could not find actions for tokens " + tokensAsSet, clazz, annotation);
            return null;
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

    private TypeSpec.Builder createBuilder(Element element, GenerateEDSL generateEDSL, ClassName name) {
        final TypeSpec.Builder builder = TypeSpec.classBuilder(name)
                                                 .addJavadoc(
                                                         "This is an auto-generated embedded domain-specific language for {@link $T}.\n",
                                                         super.processingEnv.getTypeUtils().erasure(element.asType()))
                                                 .addAnnotation(super.createGeneratedAnnotation(element));

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
}
