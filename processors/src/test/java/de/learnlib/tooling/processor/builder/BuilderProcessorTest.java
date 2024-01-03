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
package de.learnlib.tooling.processor.builder;

import java.io.IOException;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.CompilationSubject;
import com.google.testing.compile.Compiler;
import de.learnlib.tooling.Util;
import de.learnlib.tooling.it.builder.DisablingBuilderIT;
import de.learnlib.tooling.it.builder.DisablingBuilderITResult;
import de.learnlib.tooling.it.builder.DocBuilderIT;
import de.learnlib.tooling.it.builder.DocBuilderITResult;
import de.learnlib.tooling.it.builder.EmptyBuilderIT;
import de.learnlib.tooling.it.builder.EmptyBuilderITBuilder;
import de.learnlib.tooling.it.builder.GenericBuilderIT;
import de.learnlib.tooling.it.builder.GenericBuilderITResult;
import de.learnlib.tooling.it.builder.PackagePrivateBuilderIT;
import de.learnlib.tooling.it.builder.RenamedBuilderITResult;
import de.learnlib.tooling.it.builder.RenamingBuilderIT;
import de.learnlib.tooling.it.builder.SimpleBuilderIT;
import de.learnlib.tooling.it.builder.SimpleBuilderITResult;
import org.testng.annotations.Test;

public class BuilderProcessorTest {

    @Test
    public void testDocBuilder() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new BuilderProcessor()).compile(Util.toJFO(DocBuilderIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(DocBuilderITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(DocBuilderITResult.class).getCharContent(false));
    }

    @Test
    public void testEmptyBuilder() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new BuilderProcessor()).compile(Util.toJFO(EmptyBuilderIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(EmptyBuilderITBuilder.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(EmptyBuilderITBuilder.class).getCharContent(false));
    }

    @Test
    public void testSimpleBuilder() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new BuilderProcessor()).compile(Util.toJFO(SimpleBuilderIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(SimpleBuilderITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(SimpleBuilderITResult.class).getCharContent(false));
    }

    @Test
    public void testRenamingBuilder() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new BuilderProcessor()).compile(Util.toJFO(RenamingBuilderIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(RenamedBuilderITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(RenamedBuilderITResult.class).getCharContent(false));
    }

    @Test
    public void testDisablingBuilder() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new BuilderProcessor()).compile(Util.toJFO(DisablingBuilderIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(DisablingBuilderITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(DisablingBuilderITResult.class).getCharContent(false));
    }

    @Test
    public void testGenericBuilder() throws IOException {
        final Compilation compilation =
                Compiler.javac().withProcessors(new BuilderProcessor()).compile(Util.toJFO(GenericBuilderIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(GenericBuilderITResult.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(GenericBuilderITResult.class).getCharContent(false));
    }

    @Test
    public void testPackagePrivateBuilder() throws IOException {
        final Compilation compilation = Compiler.javac()
                                                .withProcessors(new BuilderProcessor())
                                                .compile(Util.toJFO(PackagePrivateBuilderIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(PackagePrivateBuilderITBuilder.class))
               .contentsAsUtf8String()
               .isEqualTo(Util.toJFO(PackagePrivateBuilderITBuilder.class).getCharContent(false));
    }
}
