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
package de.learnlib.tooling.annotation;

/**
 * An enum describing different generation types for documentation.
 */
public enum DocGenType {
    /**
     * Describes the automatic generation of documentation that simply references existing
     * methods/constructors/etc.&nbsp; that is being delegated to.
     */
    REFERENCE,
    /**
     * Describes the copying of existing documentation from the existing methods/constructors/etc.&nbsp; that is being
     * delegated to.
     */
    COPY,
    /**
     * Describes the omission of any generation of documentation.
     */
    NONE
}
