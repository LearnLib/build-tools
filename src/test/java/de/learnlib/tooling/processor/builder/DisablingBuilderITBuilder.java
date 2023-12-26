package de.learnlib.tooling.processor.builder;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.builder.DisablingBuilderIT;
import de.learnlib.tooling.it.refinement.SuperInterface;
import java.lang.Object;
import java.lang.String;

/**
 * This is an auto-generated builder for {@link DisablingBuilderIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.builder.BuilderProcessor",
        source = "de.learnlib.tooling.it.builder.DisablingBuilderIT"
)
final class DisablingBuilderITBuilder {
    private int a;

    private String b;

    private SuperInterface<?> c;

    private Object[] d;

    DisablingBuilderITBuilder(int a) {
        this.a = a;
        this.b = de.learnlib.tooling.it.builder.DisablingBuilderIT.Defaults.b();
    }

    int getA() {
        return this.a;
    }

    String getB() {
        return this.b;
    }

    SuperInterface<?> getC() {
        return this.c;
    }

    DisablingBuilderITBuilder withC(SuperInterface<?> c) {
        this.c = c;
        return this;
    }

    DisablingBuilderIT create(Object... d) {
        return new DisablingBuilderIT(a, b, c, d);
    }
}
