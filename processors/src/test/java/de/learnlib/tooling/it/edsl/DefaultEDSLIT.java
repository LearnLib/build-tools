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

/**
 * Simplified test based on an AutomataLib use-case.
 *
 * @param <I>
 *         input symbol type
 * @param <SP>
 *         state property type
 * @param <TP>
 *         transition property type
 * @param <A>
 *         automaton type
 */
@GenerateEDSL(name = "DefaultEDSLITResult",
              syntax = "((from (on (withProperty? (to* loop? to*))+)+)|withStateProperty|withInitial)* create")
public class DefaultEDSLIT<I, SP, TP, A> {

    private final A a;

    DefaultEDSLIT() {
        this.a = null;
    }

    @Action
    DefaultEDSLIT(A a) throws Exception {
        this.a = a;
    }

    @Action
    public void from(Object stateId) {}

    public void from(Object firstStateId, Object... otherStateIds) {}

    @Action
    public void on(I input) throws Exception {}

    @Action
    @SafeVarargs
    public final void on(I firstInput, I... otherInputs) {}

    @Action
    public void withProperty(TP transProp) {}

    @Action
    public void to(Object stateId) {}

    @Action
    public void loop() {}

    @Action(isTerminating = true)
    public A create() {
        return this.a;
    }

    @Action
    public void withInitial(Object stateId) {}

    @Action
    public void withStateProperty(SP stateProperty, Object stateId) {}
}
