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
 * A refinement mapping of (constructor) parameters for the to-be-generated refinement.
 */
@Retention(RetentionPolicy.SOURCE)
@Target({})
public @interface Mapping {

    /**
     * The type of input parameter that if matched (equality) should be replaced.
     *
     * @return the type of input parameter that if matched should be replaced
     */
    Class<?> from();

    /**
     * The replacement type for matched parameters.
     *
     * @return the replacement type for matched parameters
     */
    Class<?> to();

    /**
     * Potential nested type parameters of the {@link #to() replacement} type.
     *
     * @return potential nested type parameters of the replacement type
     */
    Generic[] generics() default {};

}