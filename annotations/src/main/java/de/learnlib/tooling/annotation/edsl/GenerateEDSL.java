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

import de.learnlib.tooling.annotation.DocGenType;
import dk.brics.automaton.RegExp;

/**
 * An annotation to indicate that an (embedded) DSL should be generated from the target class, interface, or enum. If an
 * annotated class contains one or multiple {@link Action}-annotated constructors, the generated class can be directly
 * instantiated via the respective constructors parameters.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface GenerateEDSL {

    /**
     * The (unqualified) name of the (embedded) DSL class to generate.
     *
     * @return the (unqualified) name
     */
    String name();

    /**
     * The fully qualified package name in which the generated class should be placed. By default, the same package as
     * the annotated class is used.
     *
     * @return the fully qualified package name
     */
    String packageName() default ".";

    /**
     * The syntax of the (embedded) DSL, specified as a regular expression. The expression is parsed by {@link RegExp}
     * (with {@link RegExp#COMPLEMENT} and {@link RegExp#INTERSECTION} enabled) and therefore follow {@link RegExp}s
     * syntax and precedence rules. {@link Expr (Sub-) expressions} in the form of "&lt;{@link Expr#name() name}&gt;"
     * are allowed and are {@link String#replaceAll(String, String) replaced} prior to parsing.
     *
     * @return the syntax expression
     */
    String syntax();

    /**
     * Returns the named expressions that can be used in the (embedded) DSL syntax definition. Expressions are
     * substituted in-order, should nested expressions be needed.
     *
     * @return the named expressions referenced in the syntax definition
     */
    Expr[] where() default {};

    /**
     * Specifies whether the generated class should be public. If {@code false}, a package-private class is generated.
     *
     * @return whether the generated class should be public
     */
    boolean classPublic() default true;

    /**
     * Specifies whether the constructor of the generated class should be public. If {@code false}, a package-private
     * constructor is generated.
     *
     * @return whether the constructor of the generated class should be public
     */
    boolean constructorPublic() default true;

    /**
     * Specifies how the documentation of the generated class should be constructed.
     *
     * @return the generation type for the documentation
     */
    DocGenType docGenType() default DocGenType.REFERENCE;
}
