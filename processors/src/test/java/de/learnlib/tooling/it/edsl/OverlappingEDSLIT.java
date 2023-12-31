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
package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.edsl.Action;
import de.learnlib.tooling.annotation.edsl.GenerateEDSL;

@GenerateEDSL(name = "OverlappingEDSLITResult", syntax = "(aaa a aa)*|aa")
public class OverlappingEDSLIT {

    @Action
    public OverlappingEDSLIT() {}

    @Action
    public void a() {}

    @Action
    public String aa() {
        return aa("aa");
    }

    @Action(terminating = true)
    public String aa(String arg) {
        return arg;
    }

    @Action
    public void aaa() {}
}
