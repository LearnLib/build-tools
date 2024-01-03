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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A definition of an interface.
 */
@Retention(RetentionPolicy.SOURCE)
@Target({})
public @interface Interface {

    /**
     * A reference to the interface class.
     *
     * @return a reference to the interface class
     */
    Class<?> clazz();

    /**
     * Potential nested type parameters of the {@link #clazz() referenced} interface.
     *
     * @return potential nested type parameters of the referenced interface
     */
    Generic[] generics() default {};

}
