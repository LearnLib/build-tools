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
package de.learnlib.tooling.processor.refinement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Types;

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;
import de.learnlib.tooling.annotation.refinement.GenerateRefinement;
import de.learnlib.tooling.annotation.refinement.GenerateRefinements;
import de.learnlib.tooling.annotation.refinement.Generic;
import de.learnlib.tooling.annotation.refinement.Interface;
import de.learnlib.tooling.annotation.refinement.Mapping;
import de.learnlib.tooling.processor.AbstractLearnLibProcessor;

public class RefinementProcessor extends AbstractLearnLibProcessor {

    private Types typeUtils;

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<>(Arrays.asList(GenerateRefinement.class.getName(), GenerateRefinements.class.getName()));
    }

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.typeUtils = super.processingEnv.getTypeUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        final Set<Element> elements = new HashSet<>();
        elements.addAll(roundEnv.getElementsAnnotatedWith(GenerateRefinements.class));
        elements.addAll(roundEnv.getElementsAnnotatedWith(GenerateRefinement.class));

        for (Element elem : elements) {

            super.validateClassKind(elem, GenerateRefinement.class);

            for (GenerateRefinement annotation : elem.getAnnotationsByType(GenerateRefinement.class)) {

                final TypeElement annotatedClass = (TypeElement) elem;

                final TypeSpec.Builder builder = createClass(annotatedClass, annotation);
                final Map<String, Generic> typeVarMap = addSuperClass(builder, annotatedClass, annotation);

                addInterfaces(builder, annotation);
                addConstructors(builder, annotatedClass, annotation, typeVarMap);

                try {
                    JavaFile.builder(super.getPackageName(elem, ""), builder.build())
                            .indent("    ")
                            .build()
                            .writeTo(super.processingEnv.getFiler());
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        return true;
    }

    private TypeSpec.Builder createClass(TypeElement annotatedClass, GenerateRefinement annotation) {
        final TypeSpec.Builder builder = TypeSpec.classBuilder(annotation.name())
                                                 .addModifiers(Modifier.PUBLIC)
                                                 .addJavadoc("This is an auto-generated refinement of {@link $T}.",
                                                             this.typeUtils.erasure(annotatedClass.asType()))
                                                 .addAnnotation(super.createAnnotation(annotatedClass));

        for (String typeParameter : annotation.generics()) {
            builder.addTypeVariable(TypeVariableName.get(typeParameter));
        }

        return builder;
    }

    private Map<String, Generic> addSuperClass(TypeSpec.Builder builder,
                                               TypeElement annotatedClass,
                                               GenerateRefinement annotation) {
        final List<? extends TypeParameterElement> typeParameters = annotatedClass.getTypeParameters();
        final Generic[] generics = annotation.parentGenerics();
        final Map<String, Generic> typeMap;

        assert typeParameters.size() == generics.length;

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
            final TypeMirror clazz = getClassValue(inter, Interface::clazz);
            final Generic[] generics = inter.generics();

            builder.addSuperinterface(toTypeName(clazz, generics));
        }
    }

    private void addConstructors(TypeSpec.Builder builder,
                                 TypeElement annotatedClass,
                                 GenerateRefinement annotation,
                                 Map<String, Generic> typeVarMap) {

        final Mapping[] typeMapping = annotation.typeMapping();

        for (ExecutableElement constructor : ElementFilter.constructorsIn(annotatedClass.getEnclosedElements())) {

            final TypeName classType = ClassName.get(this.typeUtils.erasure(annotatedClass.asType()));
            final MethodSpec.Builder mBuilder = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC);
            final StringJoiner superJoiner = new StringJoiner(", ", "super(", ")");
            final StringJoiner docJoiner =
                    new StringJoiner(", ", "This is an auto-generated constructor calling {@link $T#$T(", ")}.");

            final List<TypeName> arguments = new ArrayList<>(constructor.getParameters().size() + 2);
            arguments.add(classType);
            arguments.add(classType);

            for (VariableElement p : constructor.getParameters()) {
                final String name = p.getSimpleName().toString();
                final TypeName typeName = mapTypeName(p.asType(), typeMapping, typeVarMap);

                mBuilder.addParameter(typeName, name);
                superJoiner.add(name);
                docJoiner.add("$T");
                arguments.add(ClassName.get(this.typeUtils.erasure(p.asType())));
            }

            mBuilder.varargs(constructor.isVarArgs());
            if (constructor.isVarArgs() && super.requiresSafeVarargs(mBuilder)) {
                mBuilder.addAnnotation(SafeVarargs.class);
            }

            mBuilder.addJavadoc(docJoiner.toString(), arguments.toArray());
            mBuilder.addStatement(superJoiner.toString());
            builder.addMethod(mBuilder.build());
        }
    }

    private TypeName mapTypeName(TypeMirror typeMirror, Mapping[] typeMapping, Map<String, Generic> typeVarMap) {

        switch (typeMirror.getKind()) {
            case ARRAY:
                final ArrayType arrayMirror = (ArrayType) typeMirror;
                final TypeName innerTypeName = mapTypeName(arrayMirror.getComponentType(), typeMapping, typeVarMap);
                return ArrayTypeName.of(innerTypeName);
            case WILDCARD:
                final WildcardType wildcardMirror = (WildcardType) typeMirror;
                final TypeMirror extendsBound = wildcardMirror.getExtendsBound();
                if (extendsBound != null) {
                    return WildcardTypeName.subtypeOf(mapTypeName(extendsBound, typeMapping, typeVarMap));
                }
                final TypeMirror superBound = wildcardMirror.getSuperBound();
                if (superBound != null) {
                    return WildcardTypeName.supertypeOf(mapTypeName(superBound, typeMapping, typeVarMap));
                }
                return WildcardTypeName.get(wildcardMirror);
            case TYPEVAR:
                final Generic generic = typeVarMap.get(typeMirror.toString());

                // a dynamic method type variable, not present in the class definition
                if (generic == null) {
                    throw new IllegalArgumentException("Cannot handle dynamic type variable " + typeMirror);
                }

                final String value = generic.value();
                if (value != null && !value.isEmpty()) {
                    return TypeVariableName.get(value);
                } else {
                    return toTypeName(getClassValue(generic, Generic::clazz), toTypeVariableNames(generic.generics()));
                }
            case DECLARED:
                for (Mapping mapping : typeMapping) {
                    final TypeMirror from = getClassValue(mapping, Mapping::from);
                    if (this.typeUtils.isSameType(this.typeUtils.erasure(typeMirror), from)) {
                        final TypeMirror to = getClassValue(mapping, Mapping::to);
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
            final TypeMirror typeMirror = getClassValue(annotation, Generic::clazz);
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

    private <T> TypeMirror getClassValue(T obj, Function<T, Class<?>> extractor) {
        try {
            extractor.apply(obj);
            throw new IllegalStateException("Expected MirroredTypeException");
        } catch (MirroredTypeException mte) {
            return mte.getTypeMirror();
        }
    }

}
