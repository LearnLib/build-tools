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
package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.refinement.GenerateRefinement;
import de.learnlib.tooling.annotation.refinement.Generic;

@GenerateRefinement(name = "ErrorRefinementITResult",
                    parentGenerics = {@Generic(clazz = Boolean.class),
                                      @Generic(clazz = SubInterface.class, generics = "Boolean")})
public class ErrorRefinementIT<O, T1 extends SuperInterface<O>> {
    /*
     * Private constructors should not be copied because they cannot be called in the refinments.
     */
    private ErrorRefinementIT() {}
    /*
     * The refinement processor (currently) cannot handle dynamic type variables.
     */
    public <T2 extends SuperInterface2<O> & SuperInterface<O>> ErrorRefinementIT(T2 a) {}

}
