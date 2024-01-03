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

import java.io.IOException;

import de.learnlib.tooling.annotation.DocGenType;
import de.learnlib.tooling.annotation.refinement.GenerateRefinement;
import de.learnlib.tooling.annotation.refinement.Generic;
import de.learnlib.tooling.annotation.refinement.Mapping;

/**
 * An integration test to test the behavior of document generation.
 * <p>
 * It contains paragraphs.
 *
 * @param <I>
 *         input type
 * @param <O>
 *         output type
 */
@GenerateRefinement(name = "DocRefinementIT1Result",
                    generics = @Generic(value = "O", desc = "output symbol type"),
                    parentGenerics = {@Generic(clazz = String.class), @Generic("O")},
                    typeMappings = {@Mapping(from = SuperInterface.class,
                                             to = SubInterface.class,
                                             generics = @Generic("O"))},
                    docGenType = DocGenType.COPY)
@GenerateRefinement(name = "DocRefinementIT2Result",
                    parentGenerics = {@Generic(clazz = String.class), @Generic(clazz = Boolean.class)})
@GenerateRefinement(name = "DocRefinementIT3Result",
                    parentGenerics = {@Generic(clazz = String.class), @Generic(clazz = Boolean.class)},
                    docGenType = DocGenType.NONE)
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
    public DocRefinementIT(I input) {}

    /**
     * This third constructor takes all this class' inputs as arguments.
     *
     * @param input1
     *         this first input
     * @param input2
     *         the second input
     *
     * @throws IOException
     *         when things go bad
     */
    public DocRefinementIT(I input1, SuperInterface<O> input2) throws IOException {}

}

