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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;

import com.github.misberner.apcommons.util.ElementUtils;
import com.github.misberner.apcommons.util.annotations.AnnotationUtils;
import com.github.misberner.apcommons.util.methods.MethodUtils;
import com.github.misberner.apcommons.util.methods.ParameterInfo;
import com.github.misberner.apcommons.util.types.TypeUtils;
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
import de.learnlib.tooling.annotation.refinement.GenerateRefinement;
import de.learnlib.tooling.annotation.refinement.GenerateRefinements;
import de.learnlib.tooling.annotation.refinement.Generic;
import de.learnlib.tooling.annotation.refinement.Interface;
import de.learnlib.tooling.annotation.refinement.Map;
import de.learnlib.tooling.processor.AbstractLearnLibProcessor;
import org.kohsuke.MetaInfServices;

@MetaInfServices(Processor.class)
public class RefinementProcessor extends AbstractLearnLibProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<>(Arrays.asList(GenerateRefinement.class.getName(), GenerateRefinements.class.getName()));
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        final Set<Element> elements = new HashSet<>();
        elements.addAll(roundEnv.getElementsAnnotatedWith(GenerateRefinements.class));
        elements.addAll(roundEnv.getElementsAnnotatedWith(GenerateRefinement.class));

        for (Element elem : elements) {

            validateAnnotation(elem);

            final GenerateRefinement[] refinements;
            final GenerateRefinements generateRefinements = elem.getAnnotation(GenerateRefinements.class);

            if (generateRefinements != null) {
                refinements = generateRefinements.value();
            } else {
                refinements = new GenerateRefinement[] {elem.getAnnotation(GenerateRefinement.class)};
            }

            int idx = 0;

            for (GenerateRefinement annotation : refinements) {

                final TypeElement annotatedClass = (TypeElement) elem;

                final TypeSpec.Builder builder = createClass(annotatedClass, annotation);
                addGenerics(builder, annotation);
                addSuperClass(builder, annotatedClass, annotation);
                addInterfaces(builder, annotation);
                addConstructors(builder, annotatedClass, annotation, idx);

                try {
                    JavaFile.builder(ElementUtils.getPackageName(elem), builder.build())
                            .indent("    ")
                            .build()
                            .writeTo(super.processingEnv.getFiler());
                } catch (IOException e) {
                    super.error("Could not writer source: " + e.getMessage());
                }

                idx++;
            }
        }
        return true;
    }

    private void validateAnnotation(Element element) {
        if (element.getKind() != ElementKind.CLASS) {
            super.error("Annotation " + GenerateRefinement.class + " is only supported on class level");
            throw new IllegalArgumentException();
        }
    }

    private TypeSpec.Builder createClass(TypeElement annotatedClass, GenerateRefinement annotation) {
        return TypeSpec.classBuilder(annotation.name())
                       .addModifiers(Modifier.PUBLIC)
                       .addJavadoc("This is an auto-generated refinement. See the {@link $T original class}.\n",
                                   super.processingEnv.getTypeUtils().erasure(annotatedClass.asType()))
                       .addAnnotation(super.createAnnotation(annotatedClass));
    }

    private void addGenerics(TypeSpec.Builder builder, GenerateRefinement annotation) {
        for (String typeParameter : annotation.generics()) {
            builder.addTypeVariable(TypeVariableName.get(typeParameter));
        }
    }

    private void addSuperClass(TypeSpec.Builder builder, TypeElement annotatedClass, GenerateRefinement annotation) {

        final Generic[] pgens = annotation.parentGenerics();

        if (pgens.length > 0) {
            final List<TypeName> generics = new ArrayList<>(pgens.length);
            for (Generic generic : pgens) {
                generics.add(extractGeneric(generic));
            }
            builder.superclass(ParameterizedTypeName.get(ClassName.get(annotatedClass),
                                                         generics.toArray(new TypeName[0])));
        } else {
            builder.superclass(ClassName.get(annotatedClass));
        }
    }

    private void addInterfaces(TypeSpec.Builder builder, GenerateRefinement annotation) {

        for (Interface inter : annotation.interfaces()) {

            final TypeName typeName = extractClass(inter, Interface::clazz);

            if (!(typeName instanceof ClassName)) {
                throw new IllegalArgumentException("type '" + typeName + "' is not allowed as interface");
            }

            final ClassName className = (ClassName) typeName;

            final List<TypeName> generics = new ArrayList<>(annotation.interfaces().length);
            for (String generic : inter.generics()) {
                generics.add(TypeVariableName.get(generic));
            }

            builder.addSuperinterface(ParameterizedTypeName.get(className, generics.toArray(new TypeName[0])));
        }
    }

    private void addConstructors(TypeSpec.Builder builder,
                                 TypeElement annotatedClass,
                                 GenerateRefinement annotation,
                                 int idx) {

        final AnnotationMirror mirror;
        final AnnotationMirror generateRefinementsMirror =
                AnnotationUtils.findAnnotationMirror(annotatedClass, GenerateRefinements.class);

        if (generateRefinementsMirror != null) {
            final List<? extends AnnotationValue> values =
                    find(generateRefinementsMirror, "value", Collections.emptyList());
            mirror = (AnnotationMirror) values.get(idx).getValue();
        } else {
            mirror = AnnotationUtils.findAnnotationMirror(annotatedClass, GenerateRefinement.class);
        }

        final List<? extends AnnotationValue> parameterMapping =
                find(mirror, "parameterMapping", Collections.emptyList());

        for (ExecutableElement constructor : TypeUtils.getConstructors(annotatedClass)) {

            final MethodSpec.Builder mBuilder = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC);

            final StringJoiner javadocJoiner = new StringJoiner(", ",
                                                                "This is an auto-generated constructor. See the {@link $T#$T(",
                                                                ") original constructor}.\n");

            final int numOfConstructorParams = constructor.getParameters().size();
            final List<String> parameterNames = new ArrayList<>(numOfConstructorParams);
            final List<TypeMirror> javadocTypes = new ArrayList<>(numOfConstructorParams + 2);

            // references to <class>#<constructor>
            javadocTypes.add(super.processingEnv.getTypeUtils().erasure(annotatedClass.asType()));
            javadocTypes.add(super.processingEnv.getTypeUtils().erasure(annotatedClass.asType()));

            for (ParameterInfo info : MethodUtils.getParameterInfos(constructor)) {
                javadocJoiner.add("$T");
                javadocTypes.add(super.processingEnv.getTypeUtils().erasure(info.getType()));
                parameterNames.add(info.getName());

                if (info.isVarArgs()) {
                    mBuilder.varargs(true);
                    mBuilder.addAnnotation(SafeVarargs.class);
                }

                final TypeName typeName = buildTypeName(annotation, info.getType(), parameterMapping, info.isVarArgs());

                mBuilder.addParameter(typeName, info.getName());
            }

            final StringJoiner sj = new StringJoiner(", ", "super(", ")");
            parameterNames.forEach(sj::add);

            mBuilder.addStatement(CodeBlock.of(sj.toString(), parameterNames.toArray()));
            mBuilder.addJavadoc(javadocJoiner.toString(), javadocTypes.toArray());
            builder.addMethod(mBuilder.build());
        }
    }

    private TypeName buildTypeName(GenerateRefinement annotation,
                                   TypeMirror typeMirror,
                                   List<? extends AnnotationValue> parameterMapping,
                                   boolean isVarArgs) {

        final TypeMirror typeToCompare;

        if (isVarArgs && typeMirror.getKind() == TypeKind.ARRAY) {
            typeToCompare = ((ArrayType) typeMirror).getComponentType();
        } else {
            typeToCompare = typeMirror;
        }

        ClassName replacementClass = null;
        int i = 0;
        boolean isArray = false;
        for (AnnotationValue parameter : parameterMapping) {
            AnnotationMirror parameterMirror = (AnnotationMirror) parameter.getValue();
            TypeMirror fromAttribute = find(parameterMirror, "from", null);
            if (super.processingEnv.getTypeUtils()
                                   .isSameType(super.processingEnv.getTypeUtils().erasure(typeToCompare),
                                               fromAttribute)) {
                final TypeMirror toAttribute = find(parameterMirror, "to", null);

                if (toAttribute instanceof DeclaredType) {
                    replacementClass =
                            ClassName.get(super.processingEnv.getElementUtils().getTypeElement(toAttribute.toString()));
                } else if (toAttribute instanceof ArrayType) {
                    isArray = true;
                    replacementClass = ClassName.get(super.processingEnv.getElementUtils()
                                                                        .getTypeElement(((ArrayType) toAttribute).getComponentType()
                                                                                                                 .toString()));
                } else {
                    throw new IllegalArgumentException("Cannot handle '" + toAttribute + '\'');
                }

                break;
            }
            i++;
        }

        if (replacementClass != null) {
            final Map[] parametersAnn = annotation.parameterMapping();
            final boolean isWildcard = typeMirror.getKind() == TypeKind.WILDCARD;
            final Map map = parametersAnn[i];
            final String[] gens = map.withGenerics();
            final Generic[] cgens = map.withComplexGenerics();

            final List<TypeName> generics = new ArrayList<>(gens.length + cgens.length);

            for (String generic : gens) {
                generics.add(TypeVariableName.get(generic));
            }
            for (Generic generic : cgens) {
                generics.add(extractGeneric(generic));
            }

            final TypeName parameterizedTypeName;

            if (!generics.isEmpty()) {
                parameterizedTypeName = ParameterizedTypeName.get(replacementClass, generics.toArray(new TypeName[0]));
            } else {
                parameterizedTypeName = replacementClass;
            }

            final TypeName typeName;

            if (isWildcard) {
                if (((WildcardType) typeMirror).getExtendsBound() != null) {
                    typeName = WildcardTypeName.subtypeOf(parameterizedTypeName);
                } else {
                    typeName = WildcardTypeName.supertypeOf(parameterizedTypeName);
                }
            } else {
                typeName = parameterizedTypeName;
            }

            if (isArray || isVarArgs) {
                return ArrayTypeName.of(typeName);
            }

            return typeName;
        } else { // no replacement

            if (typeMirror.getKind() == TypeKind.DECLARED) {
                final DeclaredType declaredType = (DeclaredType) typeMirror;

                if (declaredType.getTypeArguments().isEmpty()) {
                    return TypeName.get(typeMirror);
                }

                final List<TypeName> genericTypeNames = new ArrayList<>(declaredType.getTypeArguments().size());

                for (TypeMirror t : declaredType.getTypeArguments()) {
                    genericTypeNames.add(buildTypeName(annotation, t, parameterMapping, false));
                }

                return ParameterizedTypeName.get(ClassName.get(this.processingEnv.getElementUtils()
                                                                                 .getTypeElement(declaredType.asElement()
                                                                                                             .toString())),
                                                 genericTypeNames.toArray(new TypeName[0]));
            }

            return TypeName.get(typeMirror);
        }
    }

    private TypeName extractGeneric(Generic annotation) {

        final TypeName typeName = extractClass(annotation, Generic::clazz);

        if (!ClassName.get(Void.class).equals(typeName)) { // no default value

            if (annotation.generics().length > 0) {
                if (!(typeName instanceof ClassName)) {
                    throw new IllegalArgumentException("Arrays are not allowed to have generics");
                }

                final ClassName className = (ClassName) typeName;
                final List<TypeName> genericModels = new ArrayList<>(annotation.generics().length);

                for (String generic : annotation.generics()) {
                    genericModels.add(TypeVariableName.get(generic));
                }

                return ParameterizedTypeName.get(className, genericModels.toArray(new TypeName[0]));
            }

            return typeName;
        } else if (!annotation.value().isEmpty()) {
            return TypeVariableName.get(annotation.value());
        } else {
            throw new IllegalArgumentException();
        }
    }

    private <T> TypeName extractClass(T obj, Function<T, Class<?>> extractor) {
        try {
            final Class<?> clazz = extractor.apply(obj);
            return ClassName.get(clazz);
        } catch (MirroredTypeException mte) {
            final TypeMirror typeMirror = mte.getTypeMirror();
            if (typeMirror instanceof DeclaredType) {
                DeclaredType classTypeMirror = (DeclaredType) typeMirror;
                TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
                return ClassName.get(classTypeElement);
            } else if (typeMirror instanceof ArrayType) {
                ArrayType arrayTypeMirror = (ArrayType) typeMirror;
                return ArrayTypeName.get(arrayTypeMirror);
            } else {
                throw mte;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T find(AnnotationMirror mirror, String name, T defaultValue) {
        final AnnotationValue av = AnnotationUtils.findAnnotationValue(mirror, name);
        return av == null ? defaultValue : (T) av.getValue();
    }

}
