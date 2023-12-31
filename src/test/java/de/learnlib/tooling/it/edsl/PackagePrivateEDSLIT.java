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
package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.edsl.Action;
import de.learnlib.tooling.annotation.edsl.GenerateEDSL;

@GenerateEDSL(packageName = "de.learnlib.tooling.processor.edsl",
              name = "PackagePrivateEDSLITResult",
              syntax = "done",
              classPublic = false,
              constructorPublic = false)
public class PackagePrivateEDSLIT {

    @Action(isTerminating = true)
    public String done() {
        return "done";
    }
}
