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
 * Markup annotation for providing additional information about an option used in a builder.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
public @interface Option {

    /**
     * The name of the option. If empty, the name of the attribute is used. Use {@link GenerateBuilder#SUPPRESS} to
     * disable generating any methods related to this field.
     *
     * @return the name of the option
     */
    String name() default "";

    /**
     * The name of the setter for this option. If empty, {@link GenerateBuilder#setterPrefix()} + the option name will
     * be used. Use {@link GenerateBuilder#SUPPRESS} to disable generating a setter.
     *
     * @return the name of the setter for this option
     */
    String setterName() default "";

    /**
     * The name of the getter for this option. If empty, {@link GenerateBuilder#getterPrefix()} + the option name will
     * be used. Use {@link GenerateBuilder#SUPPRESS} to disable generating a getter.
     *
     * @return the name of the getter for this option
     */
    String getterName() default "";

    /**
     * The name of the with-method for this option. If empty, {@link GenerateBuilder#withPrefix()} + the option name
     * will be used. Use {@link GenerateBuilder#SUPPRESS} to disable generating a with-method.
     *
     * @return the name of the with-method for this option
     */
    String withName() default "";

    /**
     * A flag indicating, whether or not this parameter is required when instantiating the builder, i.e., a parameter
     * for the constructor of the builder.
     *
     * @return a flag indicating, whether or not this parameter is required when instantiating the builder
     */
    boolean requiredOnInstantiation() default false;

    /**
     * A flag indicating, whether or not this parameter is required when creating the object instance, i.e., a parameter
     * of the {@link GenerateBuilder#createName() create} method of the builder.
     *
     * @return a flag indicating, whether or not this parameter is required when creating the object instance
     */
    boolean requiredOnCreation() default false;
}
