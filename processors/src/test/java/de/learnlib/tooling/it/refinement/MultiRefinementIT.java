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
package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.refinement.GenerateRefinement;
import de.learnlib.tooling.annotation.refinement.Generic;
import de.learnlib.tooling.annotation.refinement.Mapping;

@GenerateRefinement(name = "MultiRefinementITResult1",
                    generics = "M",
                    parentGenerics = @Generic("M"),
                    typeMapping = {@Mapping(from = Object.class, to = String.class),
                                   @Mapping(from = SuperInterface.class,
                                            to = SubInterface.class,
                                            generics = @Generic(clazz = SuperInterface2.class, generics = "M"))})
@GenerateRefinement(name = "MultiRefinementITResult2", parentGenerics = @Generic(clazz = String[].class))
public class MultiRefinementIT<I> implements SuperInterface<I> {

    public MultiRefinementIT(int[] param1, Object[] param2) {}

    @SafeVarargs
    public MultiRefinementIT(int[] param1, Object[] param2, SuperInterface<? extends SuperInterface2<?>>... param3) {}

}
