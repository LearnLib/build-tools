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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Definition of a generic parameter.
 */
@Retention(RetentionPolicy.SOURCE)
@Target({})
public @interface Generic {

    /**
     * A literal representation of a type parameter (e.g. defined in {@link GenerateRefinement#generics()}). A non-empty
     * value has a higher precedence over any {@link #clazz()} parameter.
     *
     * @return a literal representation of a type parameter
     */
    String value() default "";

    /**
     * A referential representation of a type parameter.
     *
     * @return a referential representation of a type parameter
     */
    Class<?> clazz() default Void.class;

    /**
     * Potential nested type parameters of the referenced {@link #clazz() referential} type parameter.
     *
     * @return potential nested type parameters of the referenced referential type parameter
     */
    String[] generics() default {};

}