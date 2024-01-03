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

@GenerateRefinement(name = "WarningRefinementITResult",
                    parentGenerics = {@Generic(clazz = Boolean.class),
                                      @Generic(clazz = SubInterface.class, generics = "Boolean")})
public class WarningRefinementIT<O, T1 extends SuperInterface<O>> {

    private final SuperInterface<O> super1;
    private final SuperInterface2<O> super2;

    public <T2 extends SuperInterface2<O> & SuperInterface<O>> WarningRefinementIT(T2 a) {
        this.super1 = a;
        this.super2 = a;
    }

    public WarningRefinementIT(T1 a) {
        this.super1 = a;
        this.super2 = null;
    }

}
