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
package de.learnlib.tooling.annotation.refinement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.learnlib.tooling.annotation.DocGenType;

/**
 * An annotation to indicate that a subclass with the specified configuration should be generated. Currently only
 * supports classes and the modification of type parameters, constructor parameters, and implemented interfaces. The
 * generated classes have a matching constructors for each parent constructor if possible, i.e., private constructors
 * are ignored.
 */
@Repeatable(GenerateRefinements.class)
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface GenerateRefinement {

    /**
     * The (unqualified) name of the refinement to generate.
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
     * The type variables the refinement should have. The {@link Generic#desc()} may be used to provide additional
     * information for the generation of documentation.
     *
     * @return the type variables the refinement should have
     */
    Generic[] generics() default {};

    /**
     * The values for the generics of the parent (i.e. the class annotated by {@code this} annotation) class.
     *
     * @return the values for the generics of the parent class
     */
    Generic[] parentGenerics() default {};

    /**
     * Mappings of type specializations for the refinement's constructor parameters.
     *
     * @return mappings of type specializations for the refinement's constructor parameters
     */
    Mapping[] typeMappings() default {};

    /**
     * Additional interfaces that the refinement should implement.
     *
     * @return additional interfaces that the refinement should implement
     */
    Interface[] interfaces() default {};

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
