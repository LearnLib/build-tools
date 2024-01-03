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

import java.util.Collection;
import java.util.function.Consumer;

import de.learnlib.tooling.annotation.refinement.GenerateRefinement;
import de.learnlib.tooling.annotation.refinement.Generic;
import de.learnlib.tooling.annotation.refinement.Mapping;

@GenerateRefinement(name = "GenericRefinementITResult",
                    parentGenerics = {@Generic(clazz = String.class), @Generic(clazz = Boolean.class)},
                    typeMappings = {@Mapping(from = SuperInterface.class,
                                             to = SubInterface.class,
                                             generics = @Generic(clazz = String.class)),
                                    @Mapping(from = SubInterface.class,
                                            to = SuperInterface.class,
                                            generics = @Generic(clazz = String.class))})
public class GenericRefinementIT<I, O> {

    public GenericRefinementIT(O a, SuperInterface<?>... b) {}

    @SafeVarargs
    public GenericRefinementIT(Collection<? extends SuperInterface<I>> a, I... b) {}

    public GenericRefinementIT(Consumer<? super SubInterface<I>> a) {}

}
