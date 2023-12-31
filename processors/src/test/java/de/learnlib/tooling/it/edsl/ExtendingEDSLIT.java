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
import de.learnlib.tooling.annotation.edsl.Expr;
import de.learnlib.tooling.annotation.edsl.GenerateEDSL;
import de.learnlib.tooling.it.refinement.SuperInterface;

@GenerateEDSL(name = "ExtendingEDSLITResult",
              syntax = "(<transOrAcc>)* withInitial (<transOrAcc>)* create",
              where = {@Expr(name = "transOrAcc", syntax = "(from (on (loop|to))+)+|withAccepting")})
public class ExtendingEDSLIT<I, A extends SuperInterface<?>> extends DefaultEDSLIT<I, Boolean, Void, A> {

    @Action
    ExtendingEDSLIT(A automaton) throws Exception {
        super(automaton);
    }

    @Action
    public void withAccepting(Object stateId) {}

    @Override
    @Action
    public void from(Object firstStateId, Object... otherStateIds) {
        super.from(firstStateId, otherStateIds);
    }

    @Override
    public void withStateProperty(Boolean stateProperty, Object stateId) {
        super.withStateProperty(stateProperty, stateId);
    }
}
