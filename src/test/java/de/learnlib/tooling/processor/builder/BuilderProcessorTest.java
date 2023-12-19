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
package de.learnlib.tooling.processor.builder;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.CompilationSubject;
import com.google.testing.compile.Compiler;
import de.learnlib.tooling.Util;
import de.learnlib.tooling.it.builder.DisablingBuilderIT;
import de.learnlib.tooling.it.builder.GenericBuilderIT;
import de.learnlib.tooling.it.builder.GenericBuilderITResult;
import de.learnlib.tooling.it.builder.RenamedBuilderITResult;
import de.learnlib.tooling.it.builder.RenamingBuilderIT;
import de.learnlib.tooling.it.builder.SimpleBuilderIT;
import de.learnlib.tooling.it.builder.SimpleBuilderITBuilder;
import org.testng.annotations.Test;

public class BuilderProcessorTest {

    @Test
    public void testSimpleBuilder() {
        final Compilation compilation =
                Compiler.javac().withProcessors(new BuilderProcessor()).compile(Util.toJFO(SimpleBuilderIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(SimpleBuilderITBuilder.class))
               .hasSourceEquivalentTo(Util.toJFO(SimpleBuilderITBuilder.class));
    }

    @Test
    public void testRenamingBuilder() {
        final Compilation compilation =
                Compiler.javac().withProcessors(new BuilderProcessor()).compile(Util.toJFO(RenamingBuilderIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(RenamedBuilderITResult.class))
               .hasSourceEquivalentTo(Util.toJFO(RenamedBuilderITResult.class));
    }

    @Test
    public void testDisablingBuilder() {
        final Compilation compilation =
                Compiler.javac().withProcessors(new BuilderProcessor()).compile(Util.toJFO(DisablingBuilderIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(DisablingBuilderITBuilder.class))
               .hasSourceEquivalentTo(Util.toJFO(DisablingBuilderITBuilder.class));
    }

    @Test
    public void testGenericBuilder() {
        final Compilation compilation =
                Compiler.javac().withProcessors(new BuilderProcessor()).compile(Util.toJFO(GenericBuilderIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(GenericBuilderITResult.class))
               .hasSourceEquivalentTo(Util.toJFO(GenericBuilderITResult.class));
    }
}
