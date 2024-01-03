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
package de.learnlib.tooling.annotation.builder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to indicate that a builder for the annotated constructor should be generated.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.CONSTRUCTOR)
public @interface GenerateBuilder {

    /**
     * An identifier for suppressing the generation of certain builder parts.
     */
    String SUPPRESS = "-";

    /**
     * The (unqualified) name of the generated builder. By default, the name is automatically constructed from the class
     * name of the annotated constructor + "Builder".
     *
     * @return the name of the generated builder
     */
    String name() default "";

    /**
     * The package name of the generated builder. By default, the package name is copied from the enclosing class of the
     * annotated constructor. name of the class of the annotated constructor.
     *
     * @return the package of the generated builder
     */
    String packageName() default ".";

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
     * The class containing the default values for the parameters. In order to provide a default value for a parameter,
     * the specified class must declare a static method without parameters whose name matches the corresponding
     * parameter name. By default, no default values are used.
     *
     * @return the class containing the default values for the parameters
     */
    Class<?> defaults() default Void.class;

    /**
     * The name for the create method.
     *
     * @return the name for the create method
     */
    String createName() default "create";

    /**
     * Specifies whether the generated class should be public. If {@code false}, a package-private class is generated.
     *
     * @return whether the generated class should be public
     */
    boolean classPublic() default true;

    /**
     * Specifies whether the constructor of the generated class should be public. If {@code false}, a package-private
     * constructor is generated.
     *
     * @return whether the constructor of the generated class should be public
     */
    boolean constructorPublic() default true;
}
