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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Markup annotation that denotes generated files. Note that this annotation has the retention level
 * {@link RetentionPolicy#CLASS} so that analysis tools (such as jacoco) can automatically ignore the generated files
 * without any additional configuration necessary.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Generated {

    /**
     * The the fully qualified name of the code generator.
     *
     * @return The the fully qualified name of the code generator
     */
    String[] generator();

    /**
     * The fully qualified name of the annotated class.
     *
     * @return The fully qualified name of the annotated class
     */
    String source();
}
