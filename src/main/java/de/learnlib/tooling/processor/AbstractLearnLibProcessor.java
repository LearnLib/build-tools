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
package de.learnlib.tooling.processor;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.function.Function;

import javax.annotation.processing.AbstractProcessor;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic.Kind;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import de.learnlib.tooling.annotation.Generated;

public abstract class AbstractLearnLibProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    protected void printError(String msg, Element element) {
        super.processingEnv.getMessager().printMessage(Kind.ERROR, msg, element);
    }

    protected void printError(String msg, Element element, Annotation annotation) {
        for (AnnotationMirror am : element.getAnnotationMirrors()) {
            final TypeElement ate = (TypeElement) am.getAnnotationType().asElement();
            if (ate.getQualifiedName().contentEquals(annotation.annotationType().getName())) {
                super.processingEnv.getMessager().printMessage(Kind.ERROR, msg, element, am);
                return;
            }
        }
        // fallback
        printError(msg, element);
    }

    protected void printWarning(String msg, Element element) {
        super.processingEnv.getMessager().printMessage(Kind.WARNING, msg, element);
    }

    protected void printWarning(String msg, Element element, Annotation annotation) {
        for (AnnotationMirror am : element.getAnnotationMirrors()) {
            final TypeElement ate = (TypeElement) am.getAnnotationType().asElement();
            if (ate.getQualifiedName().contentEquals(annotation.annotationType().getName())) {
                super.processingEnv.getMessager().printMessage(Kind.WARNING, msg, element, am);
                return;
            }
        }
        // fallback
        printError(msg, element);
    }

    protected AnnotationSpec createGeneratedAnnotation(Element annotatedClass) {
        return AnnotationSpec.builder(Generated.class)
                             .addMember("generator", "$S", getClass().getCanonicalName())
                             .addMember("source", "$S", annotatedClass.toString())
                             .build();
    }

    protected <T> TypeMirror getClassValue(T obj, Function<T, Class<?>> extractor) {
        try {
            extractor.apply(obj);
            throw new IllegalStateException("Expected MirroredTypeException");
        } catch (MirroredTypeException mte) {
            return mte.getTypeMirror();
        }
    }

    protected String getPackageName(Element element, String defaultValue) {
        if (defaultValue != null && !defaultValue.isEmpty()) {
            return defaultValue;
        } else {
            return super.processingEnv.getElementUtils().getPackageOf(element).getQualifiedName().toString();
        }
    }

    protected boolean requiresSafeVarargs(MethodSpec.Builder mBuilder) {
        final List<ParameterSpec> parameters = mBuilder.parameters;

        if (parameters.isEmpty()) {
            return false;
        } else {
            final ParameterSpec last = parameters.get(parameters.size() - 1);
            final TypeName type = last.type;

            return requiresSafeVarargs(type);
        }
    }

    private boolean requiresSafeVarargs(TypeName type) {
        if (type instanceof ParameterizedTypeName) {
            return true;
        } else if (type instanceof TypeVariableName) {
            return true;
        } else if (type instanceof ArrayTypeName) {
            return requiresSafeVarargs(((ArrayTypeName) type).componentType);
        } else {
            return false;
        }
    }
}
