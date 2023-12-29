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
package de.learnlib.tooling.annotation.refinement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to indicate that a subclass with the specified configuration should be generated. Currently only supports
 * classes and the modification of type parameters, constructor parameters, and implemented interfaces. The generated
 * classes have a matching constructor for each parent constrictor (if possible).
 */
@Repeatable(GenerateRefinements.class)
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface GenerateRefinement {

    /**
     * The name of the to-be-generated refinement.
     *
     * @return the name of the to-be-generated refinement
     */
    String name();

    /**
     * The type variables the refinement should have.
     *
     * @return the type variables the refinement should have
     */
    String[] generics() default {};

    /**
     * The values for the generics of the parent (i.e. the class annotated by {@code this} annotation) class.
     *
     * @return the values for the generics of the parent class
     */
    Generic[] parentGenerics() default {};

    /**
     * A mapping of specializations of parameter types for the refinement's constructor parameters.
     *
     * @return a mapping of specializations of parameter types for the refinement's constructor parameters
     */
    Mapping[] typeMapping() default {};

    /**
     * An array of additional interfaces the refinement should implement.
     *
     * @return an array of additional interfaces the refinement should implement
     */
    Interface[] interfaces() default {};

}
