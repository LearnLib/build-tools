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
package de.learnlib.tooling.processor.edsl;

import java.util.Arrays;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.CompilationSubject;
import com.google.testing.compile.Compiler;
import de.learnlib.tooling.Util;
import de.learnlib.tooling.it.edsl.DefaultEDSLIT;
import de.learnlib.tooling.it.edsl.DefaultEDSLITResult;
import de.learnlib.tooling.it.edsl.EmptyEDSLIT;
import de.learnlib.tooling.it.edsl.ExtendingEDSLIT;
import de.learnlib.tooling.it.edsl.ExtendingEDSLITResult;
import de.learnlib.tooling.it.edsl.OverlappingEDSLIT;
import de.learnlib.tooling.it.edsl.OverlappingEDSLITResult;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EDSLProcessorTest {

    @Test
    public void testDefaultEDSL() throws Exception {
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(DefaultEDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(DefaultEDSLITResult.class))
               .hasSourceEquivalentTo(Util.toJFO(DefaultEDSLITResult.class));

        // check that we can use the result as intended
        final String arg = "string";
        DefaultEDSLITResult<Integer, Void, Void, String> fluent = new DefaultEDSLITResult<>(arg);
        Assert.assertEquals(fluent.withInitial("asd").from("s1").on(2, 3).loop().on(4).create(), arg);
    }

    @Test
    public void testExtendingEDSL() throws Exception {
        // we need to compile both files, otherwise the annotations are missing on the super classes
        final Compilation compilation = Compiler.javac()
                                                .withProcessors(new EDSLProcessor())
                                                .compile(Arrays.asList(Util.toJFO(DefaultEDSLIT.class),
                                                                       Util.toJFO(ExtendingEDSLIT.class)));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(ExtendingEDSLITResult.class))
               .hasSourceEquivalentTo(Util.toJFO(ExtendingEDSLITResult.class));

        // check that we can use the result as intended
        ExtendingEDSLITResult<Integer, ?> fluent = new ExtendingEDSLITResult<>(null);
        fluent.withAccepting("qwe")
              .from("s0")
              .on(0)
              .to("s1")
              .withInitial("asd")
              .from("s1", "s2")
              .on(2, 3)
              .loop()
              .on(4)
              .to(4)
              .create();
    }

    @Test
    public void testEmptyEDSL() {
        // we need to compile both files, otherwise the annotations are missing on the super classes
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(EmptyEDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(EmptyEDSLITResult.class))
               .hasSourceEquivalentTo(Util.toJFO(EmptyEDSLITResult.class));

        // check that we can use the result as intended
        EmptyEDSLITResult fluent = new EmptyEDSLITResult();
        Assert.assertEquals(fluent.done(), "done");
    }

    @Test
    public void testOverlappingEDSL() {
        // we need to compile both files, otherwise the annotations are missing on the super classes
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(OverlappingEDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(OverlappingEDSLITResult.class))
               .hasSourceEquivalentTo(Util.toJFO(OverlappingEDSLITResult.class));

        // check that we can use the result as intended
        OverlappingEDSLITResult fluent = new OverlappingEDSLITResult();
        fluent.aaa().a().aa().aaa().a().aa();
        fluent.aa();
    }
}
