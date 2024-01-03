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
package de.learnlib.tooling.processor.edsl;

import java.io.IOException;
import java.util.Arrays;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.CompilationSubject;
import com.google.testing.compile.Compiler;
import de.learnlib.tooling.Util;
import de.learnlib.tooling.it.edsl.AnnotationEDSLIT;
import de.learnlib.tooling.it.edsl.AnnotationEDSLITResult;
import de.learnlib.tooling.it.edsl.DefaultEDSLIT;
import de.learnlib.tooling.it.edsl.DefaultEDSLITResult;
import de.learnlib.tooling.it.edsl.DocEDSLIT;
import de.learnlib.tooling.it.edsl.DocEDSLITResult;
import de.learnlib.tooling.it.edsl.EmptyEDSLIT;
import de.learnlib.tooling.it.edsl.EmptyEDSLITResult;
import de.learnlib.tooling.it.edsl.EnumEDSLIT;
import de.learnlib.tooling.it.edsl.EnumEDSLITResult;
import de.learnlib.tooling.it.edsl.Error2EDSLIT;
import de.learnlib.tooling.it.edsl.ErrorEDSLIT;
import de.learnlib.tooling.it.edsl.ExtendingEDSLIT;
import de.learnlib.tooling.it.edsl.ExtendingEDSLITResult;
import de.learnlib.tooling.it.edsl.InterfaceEDSLIT;
import de.learnlib.tooling.it.edsl.InterfaceEDSLITResult;
import de.learnlib.tooling.it.edsl.OverlappingEDSLIT;
import de.learnlib.tooling.it.edsl.OverlappingEDSLITResult;
import de.learnlib.tooling.it.edsl.PackagePrivateEDSLIT;
import de.learnlib.tooling.it.edsl.TerminatingEDSLIT;
import de.learnlib.tooling.it.edsl.TerminatingEDSLITResult;
import org.mockito.Mockito;
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
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(DefaultEDSLITResult.class).getCharContent(false));

        // check that we can use the result as intended
        final String arg = "string";
        DefaultEDSLITResult<Integer, Void, Void, String> fluent = new DefaultEDSLITResult<>(arg);
        Assert.assertEquals(fluent.withInitial("asd").from("s1").on(2, 3).loop().on(4).create(), arg);
    }

    @Test
    public void testDocEDSL() throws Exception {
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(DocEDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(DocEDSLITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(DocEDSLITResult.class).getCharContent(false));
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
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(ExtendingEDSLITResult.class).getCharContent(false));

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
    public void testEmptyEDSL() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(EmptyEDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(EmptyEDSLITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(EmptyEDSLITResult.class).getCharContent(false));

        // check that we can use the result as intended
        final EmptyEDSLITResult fluent = new EmptyEDSLITResult(new EmptyEDSLIT());
        Assert.assertEquals(fluent.done(), "done");
    }

    @Test
    public void testOverlappingEDSL() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(OverlappingEDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(OverlappingEDSLITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(OverlappingEDSLITResult.class).getCharContent(false));

        // check that we can use the result as intended
        final OverlappingEDSLITResult fluent = new OverlappingEDSLITResult();
        final String arg = "arg";
        final String result = fluent.aaa().a().aa().aaa().a().aa(arg);
        Assert.assertEquals(result, arg);
    }

    @Test
    public void testTerminatingEDSL() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(TerminatingEDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeeded();
        // method is used in two transitions
        subject.hadWarningCount(2);
        subject.hadWarningContaining("Treating it as non-terminating");
        subject.generatedSourceFile(Util.toFQN(TerminatingEDSLITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(TerminatingEDSLITResult.class).getCharContent(false));

        // check that we can use the result as intended
        final TerminatingEDSLITResult fluent = new TerminatingEDSLITResult();
        fluent.aaa().a().aa().aaa().a();
        fluent.aa();
    }

    @Test
    public void testEnumEDSL() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(EnumEDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(EnumEDSLITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(EnumEDSLITResult.class).getCharContent(false));

        // check that we can use the result as intended
        final EnumEDSLITResult fluent = new EnumEDSLITResult(EnumEDSLIT.ENUM_EDSLIT);
        fluent.ping().pong().ping().pong().exit();
        fluent.exit();
    }

    @Test
    public void testInterfaceEDSL() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(InterfaceEDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(InterfaceEDSLITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(InterfaceEDSLITResult.class).getCharContent(false));

        final InterfaceEDSLIT mock = Mockito.mock(InterfaceEDSLIT.class);

        // check that we can use the result as intended
        final InterfaceEDSLITResult fluent = new InterfaceEDSLITResult(mock);
        fluent.ping().pong().ping().pong().exit();

        Mockito.verify(mock, Mockito.times(2)).ping();
        Mockito.verify(mock, Mockito.times(2)).pong();
        Mockito.verify(mock, Mockito.times(1)).exit();
    }

    @Test
    public void testAnnotationEDSL() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(AnnotationEDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(AnnotationEDSLITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(AnnotationEDSLITResult.class).getCharContent(false));

        final AnnotationEDSLIT mock = Mockito.mock(AnnotationEDSLIT.class);

        // check that we can use the result as intended
        final AnnotationEDSLITResult fluent = new AnnotationEDSLITResult(mock);
        fluent.ping().pong().ping().pong().exit();

        Mockito.verify(mock, Mockito.times(2)).ping();
        Mockito.verify(mock, Mockito.times(2)).pong();
        Mockito.verify(mock, Mockito.times(1)).exit();
    }

    @Test
    public void testErrorEDSL() {
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(ErrorEDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.failed();
        subject.hadErrorCount(1);
        subject.hadErrorContaining("Could not find actions");
    }

    @Test
    public void testError2EDSL() {
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(Error2EDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.failed();
        subject.hadErrorCount(1);
        subject.hadErrorContaining("syntax contains expressions");
    }

    @Test
    public void testPackagePrivateEDSL() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new EDSLProcessor()).compile(Util.toJFO(PackagePrivateEDSLIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(PackagePrivateEDSLITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(PackagePrivateEDSLITResult.class).getCharContent(false));

        // check that we can use the result as intended
        final PackagePrivateEDSLITResult fluent = new PackagePrivateEDSLITResult(new PackagePrivateEDSLIT());
        Assert.assertEquals(fluent.done(), "done");
    }
}
