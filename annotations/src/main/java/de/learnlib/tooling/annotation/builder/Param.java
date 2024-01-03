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
 * Markup annotation for providing additional information about an parameter used in a builder.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
public @interface Param {

    /**
     * The name of the parameter. By default, the name of the constructor parameter is used. Use
     * {@link GenerateBuilder#SUPPRESS} to disable generating any methods related to this parameter.
     *
     * @return the name of the parameter
     */
    String name() default "";

    /**
     * The name of the setter for this parameter. By default, {@link GenerateBuilder#setterPrefix()} + the parameter
     * name is used. Use {@link GenerateBuilder#SUPPRESS} to disable generating a setter.
     *
     * @return the name of the setter for this parameter
     */
    String setterName() default "";

    /**
     * The name of the getter for this parameter. By default, {@link GenerateBuilder#getterPrefix()} + the parameter
     * name is used. Use {@link GenerateBuilder#SUPPRESS} to disable generating a getter.
     *
     * @return the name of the getter for this parameter
     */
    String getterName() default "";

    /**
     * The name of the with-method for this parameter. By default, {@link GenerateBuilder#withPrefix()} + the parameter
     * name is used. Use {@link GenerateBuilder#SUPPRESS} to disable generating a with-method.
     *
     * @return the name of the with-method for this parameter
     */
    String withName() default "";

    /**
     * Specifies whether this parameter is required when instantiating the builder, i.e., a parameter for the
     * constructor of the builder.
     *
     * @return whether this parameter is required when instantiating the builder
     */
    boolean requiredOnInstantiation() default false;

    /**
     * Specifies whether this parameter is required when creating the object instance, i.e., a parameter of the
     * {@link GenerateBuilder#createName() build} method of the builder.
     *
     * @return a flag indicating, whether or not this parameter is required when creating the object instance
     */
    boolean requiredOnBuild() default false;
}
