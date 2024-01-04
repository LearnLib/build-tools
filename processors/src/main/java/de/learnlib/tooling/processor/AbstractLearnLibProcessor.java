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
package de.learnlib.tooling.processor;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic.Kind;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import com.sun.source.util.DocTrees;
import de.learnlib.tooling.annotation.DocGenType;
import de.learnlib.tooling.annotation.Generated;

public abstract class AbstractLearnLibProcessor extends AbstractProcessor {

    private Messager messager;
    protected Types typeUtils;
    protected Elements elementUtils;
    protected DocTrees docUtils;

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = super.processingEnv.getMessager();
        this.typeUtils = super.processingEnv.getTypeUtils();
        this.elementUtils = super.processingEnv.getElementUtils();
        this.docUtils = DocTrees.instance(processingEnv);
    }

    protected void printError(String msg, Element element) {
        messager.printMessage(Kind.ERROR, msg, element);
    }

    protected void printError(String msg, Element element, Annotation annotation) {
        for (AnnotationMirror am : element.getAnnotationMirrors()) {
            final TypeElement ate = (TypeElement) am.getAnnotationType().asElement();
            if (ate.getQualifiedName().contentEquals(annotation.annotationType().getName())) {
                messager.printMessage(Kind.ERROR, msg, element, am);
                return;
            }
        }
        // fallback
        printError(msg, element);
    }

    protected void printWarning(String msg, Element element) {
        messager.printMessage(Kind.WARNING, msg, element);
    }

    protected void printWarning(String msg, Element element, Annotation annotation) {
        for (AnnotationMirror am : element.getAnnotationMirrors()) {
            final TypeElement ate = (TypeElement) am.getAnnotationType().asElement();
            if (ate.getQualifiedName().contentEquals(annotation.annotationType().getName())) {
                messager.printMessage(Kind.WARNING, msg, element, am);
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

    protected void addReferentialDocumentation(ExecutableElement e,
                                               DocGenType type,
                                               Consumer<CodeBlock> consumer,
                                               String prefix,
                                               String suffix) {

        switch (type) {
            case REFERENCE:
                final Types typeUtils = super.processingEnv.getTypeUtils();
                final CodeBlock.Builder docBuilder = CodeBlock.builder()
                                                              .add("$L{@link $T#$N(",
                                                                   prefix,
                                                                   typeUtils.erasure(e.getEnclosingElement().asType()),
                                                                   e.toString().split("\\(")[0]);
                final CodeBlock.Builder paramBuilder = CodeBlock.builder();

                final List<? extends VariableElement> parameters = e.getParameters();
                final List<CodeBlock> paramTypes = new ArrayList<>(parameters.size());

                for (VariableElement p : parameters) {
                    paramTypes.add(CodeBlock.of("$T", typeUtils.erasure(p.asType())));
                    paramBuilder.add("@param $N maps to the {@code $N} parameter of the delegate\n",
                                     p.getSimpleName(),
                                     p.getSimpleName());
                }
                docBuilder.add(CodeBlock.join(paramTypes, ", "));
                docBuilder.add(")}$L\n", suffix);
                docBuilder.add(paramBuilder.build());

                for (TypeMirror thrownType : e.getThrownTypes()) {
                    docBuilder.add("@throws $T if the call to the delegate throws this exception\n", TypeName.get(thrownType));
                }

                consumer.accept(docBuilder.build());
                return;
            case COPY:
                final String doc = super.processingEnv.getElementUtils().getDocComment(e);
                if (doc != null) {
                    consumer.accept(CodeBlock.of(doc));
                }
                return;
            case NONE:
            default:
                // do nothing
        }
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
        if (".".equals(defaultValue)) {
            return super.processingEnv.getElementUtils().getPackageOf(element).getQualifiedName().toString();
        } else {
            return defaultValue;
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
