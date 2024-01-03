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
package de.learnlib.tooling.processor;

import java.util.function.Consumer;

import com.squareup.javapoet.CodeBlock;
import com.sun.source.doctree.ParamTree;
import com.sun.source.util.DocTreeScanner;

public class ParamVisitor extends DocTreeScanner<Void, Void> {

    private final Consumer<CodeBlock> consumer;

    public ParamVisitor(Consumer<CodeBlock> consumer) {
        this.consumer = consumer;
    }

    @Override
    public Void visitParam(ParamTree node, Void unused) {
        assert node.isTypeParameter();
        consumer.accept(CodeBlock.of("@param <$L> $L\n", node.getName().toString(), node.getDescription().toString()));
        return super.visitParam(node, unused);
    }
}
