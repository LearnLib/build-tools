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
import de.learnlib.tooling.annotation.refinement.Interface;
import de.learnlib.tooling.annotation.refinement.Map;

@GenerateRefinement(name = "SingleRefinementITResult",
                    generics = "M",
                    parentGenerics = {@Generic("M"), @Generic(clazz = Boolean.class)},
                    parameterMapping = {@Map(from = SuperInterface.class, to = SubInterface.class, withGenerics = "M"),
                                        @Map(from = SuperInterface2.class,
                                             to = SubInterface2.class,
                                             withComplexGenerics = @Generic(clazz = Boolean.class))},
                    interfaces = @Interface(clazz = SubInterface.class, generics = "Boolean"))
public class SingleRefinementIT<I, O> implements SuperInterface<O> {

    public SingleRefinementIT(int param1,
                              SuperInterface<I> param2,
                              String param3,
                              SuperInterface2<? extends O> param4) {}

}
