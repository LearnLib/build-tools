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
package de.learnlib.tooling.processor.refinement;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.CompilationSubject;
import com.google.testing.compile.Compiler;
import de.learnlib.tooling.Util;
import de.learnlib.tooling.it.refinement.MultiRefinementIT;
import de.learnlib.tooling.it.refinement.MultiRefinementITResult1;
import de.learnlib.tooling.it.refinement.MultiRefinementITResult2;
import de.learnlib.tooling.it.refinement.SimpleRefinementIT;
import de.learnlib.tooling.it.refinement.SimpleRefinementITResult;
import de.learnlib.tooling.it.refinement.SingleRefinementIT;
import de.learnlib.tooling.it.refinement.SingleRefinementITResult;
import org.testng.annotations.Test;

public class RefinementProcessorTest {

    @Test
    public void testSimpleRefinement() {
        final Compilation compilation = Compiler.javac()
                                                .withProcessors(new RefinementProcessor())
                                                .compile(Util.toJFO(SimpleRefinementIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(SimpleRefinementITResult.class))
               .hasSourceEquivalentTo(Util.toJFO(SimpleRefinementITResult.class));
    }

    @Test
    public void testSingleRefinement() {
        final Compilation compilation = Compiler.javac()
                                                .withProcessors(new RefinementProcessor())
                                                .compile(Util.toJFO(SingleRefinementIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(SingleRefinementITResult.class))
               .hasSourceEquivalentTo(Util.toJFO(SingleRefinementITResult.class));
    }

    @Test
    public void testMultiRefinement() {
        final Compilation compilation = Compiler.javac()
                                                .withProcessors(new RefinementProcessor())
                                                .compile(Util.toJFO(MultiRefinementIT.class));

        final CompilationSubject subject = CompilationSubject.assertThat(compilation);
        subject.succeededWithoutWarnings();
        subject.generatedSourceFile(Util.toFQN(MultiRefinementITResult1.class))
               .hasSourceEquivalentTo(Util.toJFO(MultiRefinementITResult1.class));
        subject.generatedSourceFile(Util.toFQN(MultiRefinementITResult2.class))
               .hasSourceEquivalentTo(Util.toJFO(MultiRefinementITResult2.class));
    }
}
