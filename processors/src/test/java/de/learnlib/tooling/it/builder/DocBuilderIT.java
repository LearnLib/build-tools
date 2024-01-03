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
package de.learnlib.tooling.it.builder;

import de.learnlib.tooling.annotation.builder.GenerateBuilder;

/**
 * This is an integration tests that tests the copying of @param descriptions.
 *
 * @param <T1>
 *         the first type
 * @param <T2>
 *         the second type description
 *         that spans over multiple lines
 */
public class DocBuilderIT<T1, T2> {

    @GenerateBuilder(name = "DocBuilderITResult")
    public DocBuilderIT() {}

}
