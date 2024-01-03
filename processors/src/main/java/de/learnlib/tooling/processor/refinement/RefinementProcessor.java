/* Copyright (C) 2024 TU Dortmund University
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
package de.learnlib.tooling.processor.refinement;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements.Origin;

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;
import com.sun.source.doctree.DocCommentTree;
import de.learnlib.tooling.annotation.DocGenType;
import de.learnlib.tooling.annotation.refinement.GenerateRefinement;
import de.learnlib.tooling.annotation.refinement.GenerateRefinements;
import de.learnlib.tooling.annotation.refinement.Generic;
import de.learnlib.tooling.annotation.refinement.Interface;
import de.learnlib.tooling.annotation.refinement.Mapping;
import de.learnlib.tooling.processor.AbstractLearnLibProcessor;
import de.learnlib.tooling.processor.CommentVisitor;

public class RefinementProcessor extends AbstractLearnLibProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<>(Arrays.asList(GenerateRefinement.class.getName(), GenerateRefinements.class.getName()));
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        for (Element elem : roundEnv.getElementsAnnotatedWithAny(Set.of(GenerateRefinements.class,
                                                                        GenerateRefinement.class))) {

            if (elem.getKind() != ElementKind.CLASS) {
                super.printWarning("Generating refinements is only supported for classes. Skipping...", elem);
                continue;
            }

            // cast is fine because annotation is only allowed on ElementType.TYPE
            final TypeElement annotatedClass = (TypeElement) elem;

            for (GenerateRefinement annotation : elem.getAnnotationsByType(GenerateRefinement.class)) {

                final TypeSpec.Builder builder = createClass(annotatedClass, annotation);
                final Map<String, Generic> typeVarMap = addSuperClass(builder, annotatedClass, annotation);

                if (typeVarMap == null) {
                    continue;
                }

                addInterfaces(builder, annotation);
                addConstructors(builder, annotatedClass, annotation, typeVarMap);

                final JavaFile file =
                        JavaFile.builder(super.getPackageName(elem, annotation.packageName()), builder.build())
                                .indent("    ")
                                .build();
                try {
                    file.writeTo(super.processingEnv.getFiler());
                } catch (IOException e) {
                    super.printError("Could not write file: " + e.getMessage(), elem);
                }
            }
        }
        return true;
    }

    private TypeSpec.Builder createClass(TypeElement annotatedClass, GenerateRefinement annotation) {
        final TypeSpec.Builder builder =
                TypeSpec.classBuilder(annotation.name()).addAnnotation(super.createGeneratedAnnotation(annotatedClass));

        if (annotation.classPublic()) {
            builder.addModifiers(Modifier.PUBLIC);
        }

        switch (annotation.docGenType()) {
            case REFERENCE:
                builder.addJavadoc("A type-specific refinement of {@link $T}.\n", ClassName.get(annotatedClass));
                break;
            case COPY:
                final DocCommentTree docCommentTree = super.docUtils.getDocCommentTree(annotatedClass);
                final CommentVisitor commentVisitor = new CommentVisitor(builder::addJavadoc);
                commentVisitor.scan(docCommentTree, null);
                break;
            case NONE:
            default:
                // do nothing
        }

        for (Generic generic : annotation.generics()) {
            final String name = generic.value();
            builder.addTypeVariable(TypeVariableName.get(name));
            builder.addJavadoc("@param <$L> $L\n", name, generic.desc());
        }

        return builder;
    }

    private Map<String, Generic> addSuperClass(TypeSpec.Builder builder,
                                               TypeElement annotatedClass,
                                               GenerateRefinement annotation) {
        final List<? extends TypeParameterElement> typeParameters = annotatedClass.getTypeParameters();
        final Generic[] generics = annotation.parentGenerics();

        if (typeParameters.size() != generics.length) {
            super.printError("The number of parent generics does not match the actual number of type parameters",
                             annotatedClass,
                             annotation);
            return null;
        }

        final Map<String, Generic> typeMap;

        if (typeParameters.isEmpty()) {
            typeMap = Collections.emptyMap();
        } else {
            typeMap = new HashMap<>();
            for (int i = 0; i < typeParameters.size(); i++) {
                typeMap.put(typeParameters.get(i).toString(), generics[i]);
            }
        }
        builder.superclass(toTypeName(annotatedClass.asType(), generics));
        return typeMap;
    }

    private void addInterfaces(TypeSpec.Builder builder, GenerateRefinement annotation) {

        for (Interface inter : annotation.interfaces()) {
            final TypeMirror clazz = super.getClassValue(inter, Interface::clazz);
            final Generic[] generics = inter.generics();

            builder.addSuperinterface(toTypeName(clazz, generics));
        }
    }

    private void addConstructors(TypeSpec.Builder builder,
                                 TypeElement annotatedClass,
                                 GenerateRefinement annotation,
                                 Map<String, Generic> typeVarMap) {

        final Mapping[] typeMapping = annotation.typeMappings();
        final List<ExecutableElement> constructors = ElementFilter.constructorsIn(annotatedClass.getEnclosedElements());
        final boolean onlyDefault =
                constructors.size() == 1 && super.elementUtils.getOrigin(constructors.get(0)) != Origin.EXPLICIT;

        if (!onlyDefault) {
            constructor:
            for (ExecutableElement constructor : constructors) {

                if (!constructor.getModifiers().contains(Modifier.PRIVATE)) {
                    final MethodSpec.Builder mBuilder = MethodSpec.constructorBuilder();
                    final StringJoiner superJoiner = new StringJoiner(", ", "super(", ")");

                    for (VariableElement p : constructor.getParameters()) {
                        final String name = p.getSimpleName().toString();
                        final TypeName typeName = mapTypeName(p.asType(), typeMapping, typeVarMap);

                        if (typeName == null) {
                            super.printWarning(
                                    "Constructor uses a dynamic type variable which are not supported. Skipping...",
                                    constructor);
                            continue constructor;
                        }

                        mBuilder.addParameter(typeName, name);
                        superJoiner.add(name);
                    }

                    mBuilder.addStatement(superJoiner.toString());
                    mBuilder.varargs(constructor.isVarArgs());
                    mBuilder.addExceptions(constructor.getThrownTypes()
                                                      .stream()
                                                      .map(TypeName::get)
                                                      .collect(Collectors.toList()));

                    if (constructor.isVarArgs() && super.requiresSafeVarargs(mBuilder)) {
                        mBuilder.addAnnotation(SafeVarargs.class);
                    }

                    if (annotation.constructorPublic()) {
                        mBuilder.addModifiers(Modifier.PUBLIC);
                    }

                    addConstructorDocumentation(constructor, annotation.docGenType(), mBuilder::addJavadoc);

                    builder.addMethod(mBuilder.build());
                }
            }

            if (builder.methodSpecs.isEmpty()) {
                super.printError("No eligible constructors found", annotatedClass);
            }
        }
    }

    private TypeName mapTypeName(TypeMirror typeMirror, Mapping[] typeMapping, Map<String, Generic> typeVarMap) {

        switch (typeMirror.getKind()) {
            case ARRAY:
                final ArrayType arrayMirror = (ArrayType) typeMirror;
                final TypeName innerTypeName = mapTypeName(arrayMirror.getComponentType(), typeMapping, typeVarMap);
                return innerTypeName == null ? null : ArrayTypeName.of(innerTypeName);
            case WILDCARD:
                final WildcardType wildcardMirror = (WildcardType) typeMirror;
                final TypeMirror extendsBound = wildcardMirror.getExtendsBound();
                if (extendsBound != null) {
                    final TypeName extendsType = mapTypeName(extendsBound, typeMapping, typeVarMap);
                    return extendsType == null ? null : WildcardTypeName.subtypeOf(extendsType);
                }
                final TypeMirror superBound = wildcardMirror.getSuperBound();
                if (superBound != null) {
                    final TypeName superType = mapTypeName(superBound, typeMapping, typeVarMap);
                    return superType == null ? null : WildcardTypeName.supertypeOf(superType);
                }
                return WildcardTypeName.get(wildcardMirror);
            case TYPEVAR:
                final Generic generic = typeVarMap.get(typeMirror.toString());

                // a dynamic method type variable, not present in the class definition
                if (generic == null) {
                    return null;
                }

                final String value = generic.value();
                if (value != null && !value.isEmpty()) {
                    return TypeVariableName.get(value);
                } else {
                    return toTypeName(super.getClassValue(generic, Generic::clazz),
                                      toTypeVariableNames(generic.generics()));
                }
            case DECLARED:
                for (Mapping mapping : typeMapping) {
                    final TypeMirror from = super.getClassValue(mapping, Mapping::from);
                    if (super.typeUtils.isSameType(super.typeUtils.erasure(typeMirror), from)) {
                        final TypeMirror to = super.getClassValue(mapping, Mapping::to);
                        final Generic[] generics = mapping.generics();
                        return toTypeName(to, generics);
                    }
                }

                final DeclaredType declaredMirror = (DeclaredType) typeMirror;
                final ClassName typeName = ClassName.get((TypeElement) declaredMirror.asElement());
                final List<? extends TypeMirror> typeArguments = declaredMirror.getTypeArguments();

                if (typeArguments.isEmpty()) {
                    return typeName;
                } else {
                    final TypeName[] generics = new TypeName[typeArguments.size()];
                    for (int i = 0; i < typeArguments.size(); i++) {
                        generics[i] = mapTypeName(typeArguments.get(i), typeMapping, typeVarMap);
                    }
                    return ParameterizedTypeName.get(typeName, generics);
                }
            default:
                return ClassName.get(typeMirror);
        }
    }

    private TypeName toTypeName(TypeMirror typeMirror, TypeName[] generics) {

        final ClassName className = ClassName.get((TypeElement) this.typeUtils.asElement(typeMirror));

        if (generics.length > 0) {
            return ParameterizedTypeName.get(className, generics);
        } else {
            return className;
        }
    }

    private TypeName toTypeName(TypeMirror typeMirror, Generic[] generics) {

        if (generics.length > 0) {
            final TypeName[] typeNames = new TypeName[generics.length];
            for (int i = 0; i < generics.length; i++) {
                typeNames[i] = extractGeneric(generics[i]);
            }

            return toTypeName(typeMirror, typeNames);
        } else {
            return ClassName.get(typeMirror);
        }
    }

    private TypeName extractGeneric(Generic annotation) {

        final String value = annotation.value();

        if (!value.isEmpty()) {
            return TypeVariableName.get(value);
        } else {
            final TypeMirror typeMirror = super.getClassValue(annotation, Generic::clazz);
            final String[] generics = annotation.generics();

            if (generics.length > 0) {
                final TypeName[] typeNames = toTypeVariableNames(generics);
                return toTypeName(typeMirror, typeNames);
            } else {
                return ClassName.get(typeMirror);
            }
        }
    }

    private TypeName[] toTypeVariableNames(String[] generics) {
        final TypeName[] typeNames = new TypeName[generics.length];
        for (int i = 0; i < generics.length; i++) {
            typeNames[i] = TypeVariableName.get(generics[i]);
        }

        return typeNames;
    }

    private void addConstructorDocumentation(ExecutableElement e, DocGenType type, Consumer<CodeBlock> consumer) {
        super.addReferentialDocumentation(e, type, consumer, "Delegates to ", ".");
    }

}
