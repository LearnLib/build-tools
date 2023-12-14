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
package de.learnlib.tooling;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Paths;

import javax.tools.JavaFileObject;

import com.google.testing.compile.JavaFileObjects;

public final class Util {

    private Util() {
        // prevent instantiation
    }

    public static <T> JavaFileObject toJFO(Class<T> clazz) {
        // defined in pom.xml / surefire configuration
        final String dir = System.getProperty("testSourceDir");
        final String pkg = clazz.getPackage().getName().replace('.', '/');
        final String name = clazz.getSimpleName() + ".java";
        final URI uri = Paths.get(dir, pkg, name).toUri();

        try {
            return JavaFileObjects.forResource(uri.toURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String toFQN(Class<T> clazz) {
        return clazz.getCanonicalName();
    }

}
