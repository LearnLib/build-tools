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
package de.learnlib.tooling.annotation.edsl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A named expression, to be used in a {@link GenerateEDSL#syntax()} definition.
 */
@Retention(RetentionPolicy.SOURCE)
@Target({})
public @interface Expr {

    /**
     * The name of this expression.
     *
     * @return the name of this expression
     */
    String name();

    /**
     * The syntax definition of this expression. The syntax follows that of {@link GenerateEDSL#syntax()}.
     *
     * @return the syntax definition of this expression
     */
    String syntax();

}
