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
package de.learnlib.tooling.annotation.builder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Markup annotation to automatically generate a builder for the annotated constructor.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.CONSTRUCTOR)
public @interface GenerateBuilder {

    /**
     * An identifier for suppressing the generation of certain builder parts.
     */
    String SUPPRESS = "-";

    /**
     * The name of the generated builder. If left empty, it will be automatically constructed from the class name of the
     * annotated constructor + "Builder".
     *
     * @return the name of the generated builder
     */
    String name() default "";

    /**
     * The package name of the generated builder. If left empty, it will be automatically constructed from the package
     * name of the class of the annotated constructor.
     *
     * @return the package of the generated builder
     */
    String packageName() default "";

    /**
     * The prefix to use for getter methods. Use {@link #SUPPRESS} to disable generating getters.
     *
     * @return the prefix to use for getter methods
     */
    String getterPrefix() default "get";

    /**
     * The prefix to use for setter methods. Use {@link #SUPPRESS} to disable generating setters.
     *
     * @return the prefix to use for setter methods
     */
    String setterPrefix() default "set";

    /**
     * The prefix to use for fluent with methods. Use {@link #SUPPRESS} to disable generating with method.
     *
     * @return the prefix to use for with methods
     */
    String withPrefix() default "with";

    /**
     * The class containing the default values for the options. If {@link Void} is specified, no default values are
     * used.
     *
     * @return the class containing the default values for the options
     */
    Class<?> defaults() default Void.class;

    /**
     * Returns whether the generated builder should be a <code>public</code> class.
     *
     * @return whether the generated builder should be public
     */
    boolean builderPublic() default true;

    /**
     * Returns whether the generated builder should be a <code>final</code> class.
     *
     * @return whether the generated builder should be final
     */
    boolean builderFinal() default true;

    /**
     * The name for the create method.
     *
     * @return the name for the create method
     */
    String createName() default "create";
}
