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

import de.learnlib.tooling.annotation.DocGenType;
import de.learnlib.tooling.annotation.edsl.Action;
import de.learnlib.tooling.annotation.edsl.GenerateEDSL;

/**
 * A custom description for {@link DocEDSLIT}.
 *
 * @param <SP>
 *         state property
 * @param <A>
 *         automaton type
 */
@GenerateEDSL(name = "DocEDSLITResult",
              syntax = "(withStateProperty|withInitial)* create",
              docGenType = DocGenType.COPY)
public class DocEDSLIT<SP, A> {

    /**
     * Marks the state identified by the given object as initial.
     *
     * @param stateId
     *         the object to identify the state
     */
    @Action
    public void withInitial(Object stateId) {}

    /*
     * do not fail in missing doc
     */
    @Action
    public void withStateProperty(SP stateProperty, Object stateId) {}

    /**
     * The return statement does not match the DSL return object, but we forced COPY mode.
     *
     * @param stateId
     *         the object to identify the state
     *
     * @return the stateId
     */
    @Action
    public Object withStateProperty(Object stateId) {
        return stateId;
    }

    /**
     * Returns the final object.
     *
     * @return the final object
     */
    @Action(terminating = true)
    public A create() {
        return null;
    }
}
