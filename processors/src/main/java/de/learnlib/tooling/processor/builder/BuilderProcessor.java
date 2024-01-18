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
package de.learnlib.tooling.processor.builder;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import de.learnlib.tooling.annotation.builder.GenerateBuilder;
import de.learnlib.tooling.annotation.builder.Param;
import de.learnlib.tooling.processor.AbstractLearnLibProcessor;

public class BuilderProcessor extends AbstractLearnLibProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(GenerateBuilder.class.getName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        for (Element elem : roundEnv.getElementsAnnotatedWith(GenerateBuilder.class)) {

            final ExecutableElement constructor = (ExecutableElement) elem;
            final GenerateBuilder annotation = constructor.getAnnotation(GenerateBuilder.class);

            final String name = getBuilderName(constructor, annotation);
            final String pkg = super.getPackageName(constructor, annotation.packageName());
            final String create = annotation.createName();
            final Iterable<Modifier> classModifiers =
                    annotation.classPublic() ? Collections.singleton(Modifier.PUBLIC) : Collections.emptyList();
            final Iterable<Modifier> constructorModifiers =
                    annotation.constructorPublic() ? Collections.singleton(Modifier.PUBLIC) : Collections.emptyList();
            final TypeElement defaultsClass = getDefaultsValue(annotation);
            final Set<String> defaultsValues = ElementFilter.methodsIn(defaultsClass.getEnclosedElements())
                                                            .stream()
                                                            .map(ee -> ee.getSimpleName().toString())
                                                            .collect(Collectors.toSet());

            final boolean isGetSuppressed = GenerateBuilder.SUPPRESS.equals(annotation.getterPrefix());
            final boolean isSetSuppressed = GenerateBuilder.SUPPRESS.equals(annotation.setterPrefix());
            final boolean isWithSuppressed = GenerateBuilder.SUPPRESS.equals(annotation.withPrefix());

            final Element clazz = constructor.getEnclosingElement();
            assert clazz.getKind() == ElementKind.CLASS;

            final ClassName builderName = ClassName.get(pkg, name);
            final TypeName builderType = getAnnotatedType(clazz, builderName);
            final TypeName targetType = ClassName.get(clazz.asType());

            final TypeSpec.Builder classBuilder = createBuilder(clazz, annotation, builderName);
            final MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                                                                    .addModifiers(constructorModifiers)
                                                                    .addJavadoc(
                                                                            "Creates a new builder (and may set default values for some parameters).\n");
            final MethodSpec.Builder createBuilder = MethodSpec.methodBuilder(create)
                                                               .addModifiers(classModifiers)
                                                               .returns(targetType)
                                                               .addJavadoc(
                                                                       "Creates a new {@link $T} instance with the configured parameters.\n",
                                                                       super.typeUtils.erasure(clazz.asType()));

            final List<? extends VariableElement> params = constructor.getParameters();
            final StringJoiner returnJoiner = new StringJoiner(", ", "return new $T(", ")");

            for (int i = 0; i < params.size(); i++) {
                final VariableElement ve = params.get(i);
                final boolean isVarArgs = constructor.isVarArgs() && i == params.size() - 1;
                final Param paramAnnotation = ve.getAnnotation(Param.class);
                final boolean isFieldSuppressed;
                final String fieldName;

                if (paramAnnotation != null) {
                    if (GenerateBuilder.SUPPRESS.equals(paramAnnotation.name())) {
                        isFieldSuppressed = true;
                        fieldName = ve.getSimpleName().toString();
                    } else {
                        isFieldSuppressed = false;
                        if (paramAnnotation.name().isEmpty()) {
                            fieldName = ve.getSimpleName().toString();
                        } else {
                            fieldName = paramAnnotation.name();
                        }
                    }
                } else {
                    isFieldSuppressed = false;
                    fieldName = ve.getSimpleName().toString();
                }

                returnJoiner.add(fieldName);
                final TypeName fieldTypeName = ClassName.get(ve.asType());

                // attribute
                classBuilder.addField(FieldSpec.builder(fieldTypeName, fieldName, Modifier.PRIVATE).build());

                // constructor
                if (paramAnnotation != null && paramAnnotation.requiredOnInstantiation()) {
                    constructorBuilder.addParameter(fieldTypeName, fieldName);
                    constructorBuilder.addStatement(CodeBlock.of("this.$N = $N", fieldName, fieldName));
                    constructorBuilder.addJavadoc("@param $N the value used to initialize parameter {@code $N}\n",
                                                  fieldName,
                                                  fieldName);
                } else if (defaultsValues.contains(fieldName)) {
                    constructorBuilder.addStatement(CodeBlock.of("this.$N = $L.$N()",
                                                                 fieldName,
                                                                 ClassName.get(defaultsClass),
                                                                 fieldName));
                }

                // getter
                if (!isGetSuppressed && !isFieldSuppressed) {
                    final String value = (paramAnnotation != null) ? paramAnnotation.getterName() : "";
                    if (!value.equals(GenerateBuilder.SUPPRESS)) {
                        final String getterName = value.isEmpty() ? capitalize(fieldName) : value;
                        final MethodSpec.Builder getterBuilder =
                                MethodSpec.methodBuilder(annotation.getterPrefix() + getterName)
                                          .addModifiers(classModifiers)
                                          .returns(fieldTypeName)
                                          .addStatement("return this.$N", fieldName)
                                          .addJavadoc("Returns the current value for the parameter {@code $N}.\n",
                                                      fieldName)
                                          .addJavadoc("@return the current value for the parameter {@code $N}",
                                                      fieldName);
                        classBuilder.addMethod(getterBuilder.build());
                    }
                }

                // setter
                if (!isSetSuppressed && !isFieldSuppressed) {
                    final String value = (paramAnnotation != null) ? paramAnnotation.setterName() : "";
                    if (!value.equals(GenerateBuilder.SUPPRESS)) {
                        final String setterName = value.isEmpty() ? capitalize(fieldName) : value;
                        final MethodSpec.Builder setterBuilder =
                                MethodSpec.methodBuilder(annotation.setterPrefix() + setterName)
                                          .addModifiers(classModifiers)
                                          .varargs(isVarArgs)
                                          .addParameter(fieldTypeName, fieldName)
                                          .addStatement("this.$N = $N", fieldName, fieldName)
                                          .addJavadoc("Sets the new value for the parameter {@code $N}.\n", fieldName)
                                          .addJavadoc("@param $N the new value for the parameter {@code $N}",
                                                      fieldName,
                                                      fieldName);
                        if (isVarArgs && super.requiresSafeVarargs(setterBuilder)) {
                            setterBuilder.addModifiers(Modifier.FINAL).addAnnotation(SafeVarargs.class);
                        }
                        classBuilder.addMethod(setterBuilder.build());
                    }
                }

                // with
                if (!isWithSuppressed && !isFieldSuppressed) {
                    final String value = (paramAnnotation != null) ? paramAnnotation.withName() : "";
                    if (!value.equals(GenerateBuilder.SUPPRESS)) {
                        final String withName = value.isEmpty() ? capitalize(fieldName) : value;
                        final MethodSpec.Builder withBuilder =
                                MethodSpec.methodBuilder(annotation.withPrefix() + withName)
                                          .addModifiers(classModifiers)
                                          .varargs(isVarArgs)
                                          .returns(builderType)
                                          .addParameter(fieldTypeName, fieldName)
                                          .addStatement("this.$N = $N", fieldName, fieldName)
                                          .addStatement("return this")
                                          .addJavadoc(
                                                  "Sets the new value for the parameter {@code $N} and returns {@code this} builder instance.\n",
                                                  fieldName)
                                          .addJavadoc("@param $N the new value for the parameter {@code $N}\n",
                                                      fieldName,
                                                      fieldName)
                                          .addJavadoc("@return the current builder instance");
                        if (isVarArgs && super.requiresSafeVarargs(withBuilder)) {
                            withBuilder.addModifiers(Modifier.FINAL).addAnnotation(SafeVarargs.class);
                        }
                        classBuilder.addMethod(withBuilder.build());
                    }
                }

                // create
                if (paramAnnotation != null && paramAnnotation.requiredOnCreation()) {
                    createBuilder.varargs(isVarArgs)
                                 .addParameter(fieldTypeName, fieldName)
                                 .addJavadoc("@param $N the value used for the parameter {@code $N}\n",
                                             fieldName,
                                             fieldName);
                    if (isVarArgs && super.requiresSafeVarargs(createBuilder)) {
                        createBuilder.addModifiers(Modifier.FINAL).addAnnotation(SafeVarargs.class);
                    }
                }
            }

            createBuilder.addStatement(returnJoiner.toString(), ClassName.get(clazz.asType()));
            createBuilder.addJavadoc("@return the created instance\n");

            for (TypeMirror ex : constructor.getThrownTypes()) {
                createBuilder.addException(TypeName.get(ex));
                createBuilder.addJavadoc("@throws $T if instantiating the object throws this exception", ex);
            }

            classBuilder.addMethod(constructorBuilder.build());
            classBuilder.addMethod(createBuilder.build());

            final JavaFile file = JavaFile.builder(pkg, classBuilder.build()).indent("    ").build();

            try {
                file.writeTo(super.processingEnv.getFiler());
            } catch (IOException e) {
                super.printError("Could not write file: " + e.getMessage(), constructor);
            }
        }
        return true;
    }

    private TypeName getAnnotatedType(Element clazz, ClassName className) {
        final DeclaredType type = (DeclaredType) clazz.asType();
        final List<? extends TypeMirror> typeArguments = type.getTypeArguments();

        if (typeArguments.isEmpty()) {
            return className;
        }

        final TypeName[] generics = new TypeName[typeArguments.size()];

        for (int i = 0; i < typeArguments.size(); i++) {
            generics[i] = TypeVariableName.get(typeArguments.get(i));
        }

        return ParameterizedTypeName.get(className, generics);
    }

    private String getBuilderName(Element element, GenerateBuilder annotation) {
        final String name = annotation.name();

        if (name.isEmpty()) {
            return element.getEnclosingElement().getSimpleName().toString() + "Builder";
        } else {
            return name;
        }
    }

    private TypeElement getDefaultsValue(GenerateBuilder annotation) {
        final TypeMirror mirror = super.getClassValue(annotation, GenerateBuilder::defaults);
        final DeclaredType dt = (DeclaredType) mirror;
        return (TypeElement) dt.asElement();
    }

    private TypeSpec.Builder createBuilder(Element element, GenerateBuilder annotation, ClassName name) {
        final TypeSpec.Builder builder =
                TypeSpec.classBuilder(name).addAnnotation(super.createGeneratedAnnotation(element));

        final DeclaredType clazz = (DeclaredType) element.asType();

        for (TypeMirror ta : clazz.getTypeArguments()) {
            TypeVariableName typeName = (TypeVariableName) ParameterizedTypeName.get(ta);
            builder.addTypeVariable(typeName);
        }

        builder.addModifiers(Modifier.FINAL);
        if (annotation.classPublic()) {
            builder.addModifiers(Modifier.PUBLIC);
        }

        builder.addJavadoc("A builder for constructing {@link $T} instances.\n",
                           super.typeUtils.erasure(element.asType()));
        super.extractParamDoc(super.elementUtils.getDocComment(element), builder::addJavadoc);

        return builder;
    }

    private String capitalize(String str) {
        if (str.isEmpty()) {
            return str;
        }

        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

}
