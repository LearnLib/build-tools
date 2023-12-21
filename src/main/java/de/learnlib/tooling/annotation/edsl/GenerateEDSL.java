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
package de.learnlib.tooling.annotation.edsl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dk.brics.automaton.RegExp;

/**
 * Generate an (embedded) DSL from the target class or interface.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface GenerateEDSL {

    /**
     * The simple, i.e., unqualified name of the (embedded) DSL class to generate.
     *
     * @return the (simple) class name
     */
    String name();

    /**
     * The fully qualified package name in which the generated (embedded)  DSL class will be placed. By default, the
     * same package as the annotated class will be used.
     *
     * @return the package name
     */
    String packageName() default "";

    /**
     * The syntax of the (embedded) DSL, specified as a regular expression. The expression will be parsed by
     * {@link RegExp} (with {@link RegExp#COMPLEMENT} and {@link RegExp#INTERSECTION} enabled) and therefore follow
     * {@link RegExp}s syntax and precedence rules. {@link Expr (Sub-) expressions} in the form of
     * "&lt;{@link Expr#name() name}&gt;" are allowed and will be {@link String#replaceAll(String, String) replaced}
     * prior to parsing.
     *
     * @return the syntax expression
     */
    String syntax();

    /**
     * Specifies whether the generated (embedded) DSL class should be declared as {@code public}. If set to
     * {@code false}, it will have package-private visibility.
     *
     * @return whether the generated DSL class should be declared as {@code public}
     */
    boolean isPublic() default true;

    /**
     * Named expressions that can be used in the embedded DSL syntax definition. Expressions should not contain other
     * expressions.
     */
    Expr[] where() default {};
}
