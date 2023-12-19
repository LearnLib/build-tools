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
package de.learnlib.tooling.it.builder;

import de.learnlib.tooling.annotation.builder.GenerateBuilder;
import de.learnlib.tooling.annotation.builder.Option;
import de.learnlib.tooling.it.refinement.SuperInterface;

public class RenamingBuilderIT {

    @GenerateBuilder(name = "RenamedBuilderITResult", withPrefix = "without", getterPrefix = "git", setterPrefix = "sit")
    public RenamingBuilderIT(@Option(name = "param1") int a,
                             String b,
                             @Option(getterName = "ccc", setterName = "ccc") SuperInterface<?> c,
                             @Option(withName = "DD") Object[] d) {}

}
