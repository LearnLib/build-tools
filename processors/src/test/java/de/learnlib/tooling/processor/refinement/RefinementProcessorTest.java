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
package de.learnlib.tooling.processor.refinement;

import java.io.IOException;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.CompilationSubject;
import com.google.testing.compile.Compiler;
import de.learnlib.tooling.Util;
import de.learnlib.tooling.it.refinement.DocRefinementIT;
import de.learnlib.tooling.it.refinement.DocRefinementIT1Result;
import de.learnlib.tooling.it.refinement.DocRefinementIT2Result;
import de.learnlib.tooling.it.refinement.DocRefinementIT3Result;
import de.learnlib.tooling.it.refinement.Error2RefinementIT;
import de.learnlib.tooling.it.refinement.ErrorRefinementIT;
import de.learnlib.tooling.it.refinement.GenericRefinementIT;
import de.learnlib.tooling.it.refinement.GenericRefinementITResult;
import de.learnlib.tooling.it.refinement.MultiRefinementIT;
import de.learnlib.tooling.it.refinement.MultiRefinementIT1Result;
import de.learnlib.tooling.it.refinement.MultiRefinementIT2Result;
import de.learnlib.tooling.it.refinement.PackagePrivateRefinementIT;
import de.learnlib.tooling.it.refinement.SimpleRefinementIT;
import de.learnlib.tooling.it.refinement.SimpleRefinementITResult;
import de.learnlib.tooling.it.refinement.SingleRefinementIT;
import de.learnlib.tooling.it.refinement.SingleRefinementITResult;
import de.learnlib.tooling.it.refinement.WarningRefinementIT;
import de.learnlib.tooling.it.refinement.WarningRefinementITResult;
import org.testng.annotations.Test;

public class RefinementProcessorTest {

    @Test
    public void testDocRefinement() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new RefinementProcessor()).compile(Util.toJFO(DocRefinementIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(DocRefinementIT1Result.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(DocRefinementIT1Result.class).getCharContent(false));
        subject.generatedSourceFile(Util.toFQN(DocRefinementIT2Result.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(DocRefinementIT2Result.class).getCharContent(false));
        subject.generatedSourceFile(Util.toFQN(DocRefinementIT3Result.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(DocRefinementIT3Result.class).getCharContent(false));
    }

    @Test
    public void testSimpleRefinement() throws IOException {
        final Compilation compilation = Compiler.javac()
                                                .withProcessors(new RefinementProcessor())
                                                .compile(Util.toJFO(SimpleRefinementIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(SimpleRefinementITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(SimpleRefinementITResult.class).getCharContent(false));
    }

    @Test
    public void testSingleRefinement() throws IOException {
        final Compilation compilation = Compiler.javac()
                                                .withProcessors(new RefinementProcessor())
                                                .compile(Util.toJFO(SingleRefinementIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(SingleRefinementITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(SingleRefinementITResult.class).getCharContent(false));
    }

    @Test
    public void testMultiRefinement() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new RefinementProcessor()).compile(Util.toJFO(MultiRefinementIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(MultiRefinementIT1Result.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(MultiRefinementIT1Result.class).getCharContent(false));
        subject.generatedSourceFile(Util.toFQN(MultiRefinementIT2Result.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(MultiRefinementIT2Result.class).getCharContent(false));
    }

    @Test
    public void testGenericRefinement() throws IOException {
        final Compilation compilation = Compiler.javac()
                                                .withProcessors(new RefinementProcessor())
                                                .compile(Util.toJFO(GenericRefinementIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(GenericRefinementITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(GenericRefinementITResult.class).getCharContent(false));
    }

    @Test
    public void testWarningRefinement() throws IOException {
        final Compilation compilation = Compiler.javac()
                                                .withProcessors(new RefinementProcessor())
                                                .compile(Util.toJFO(WarningRefinementIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeeded();
        subject.hadWarningCount(1);
        subject.hadWarningContaining("dynamic type variable");
        subject.generatedSourceFile(Util.toFQN(WarningRefinementITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(WarningRefinementITResult.class).getCharContent(false));
    }

    @Test
    public void testErrorRefinement() {
        final Compilation compilation =
                Compiler.javac().withProcessors(new RefinementProcessor()).compile(Util.toJFO(ErrorRefinementIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.failed();
        subject.hadWarningCount(1);
        subject.hadWarningContaining("dynamic type variable");
        subject.hadErrorCount(1);
        subject.hadErrorContaining("No eligible constructors");
    }

    @Test
    public void testError2Refinement() {
        final Compilation compilation = Compiler.javac()
                                                .withProcessors(new RefinementProcessor())
                                                .compile(Util.toJFO(Error2RefinementIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.failed();
        // the non erroneous class should produce no errors
        subject.hadErrorCount(1);
        subject.hadErrorContaining("number of");
    }

    @Test
    public void testPackagePrivateRefinement() throws IOException {
        final Compilation compilation = Compiler.javac()
                                                .withProcessors(new RefinementProcessor())
                                                .compile(Util.toJFO(PackagePrivateRefinementIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(PackagePrivateRefinementITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(PackagePrivateRefinementITResult.class).getCharContent(false));
    }
}
