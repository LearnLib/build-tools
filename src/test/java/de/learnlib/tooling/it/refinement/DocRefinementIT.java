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

@GenerateRefinement(name = "DocRefinementITResult",
                    generics = "O",
                    parentGenerics = {@Generic(clazz = String.class), @Generic("O")},
                    typeMapping = {@Mapping(from = SuperInterface.class,
                                            to = SubInterface.class,
                                            generics = @Generic("O"))},
                    classDoc = "This is a refinement of {@link DocRefinementITResult} that binds the type variable I to {@link String} and narrows the {@link SuperInterface} parameter to {@link SubInterface}\n" +
                               "@param <O> output type\n")
public class DocRefinementIT<I, O> {

    /**
     * This is a default constructor that uses the (Java) default values for all its internal fields.
     */
    public DocRefinementIT() {}

    /**
     * This is a second constructor stores the given input as first input and {@code null} as second.
     *
     * @param input
     *         the input to this class
     *
     * @see DocRefinementIT#DocRefinementIT(Object, SuperInterface)
     */
    public DocRefinementIT(I input) {
        this(input, null);
    }

    /**
     * This third constructor takes all this class' inputs as arguments.
     *
     * @param input1
     *         this first input
     * @param input2
     *         the second input
     */
    public DocRefinementIT(I input1, SuperInterface<O> input2) {}

}

