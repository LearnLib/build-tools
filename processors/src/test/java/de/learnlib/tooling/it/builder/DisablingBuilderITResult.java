package de.learnlib.tooling.it.builder;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.refinement.SuperInterface;
import java.lang.Object;
import java.lang.String;

@Generated(
        generator = "de.learnlib.tooling.processor.builder.BuilderProcessor",
        source = "de.learnlib.tooling.it.builder.DisablingBuilderIT"
)
public final class DisablingBuilderITResult {
    private int a;

    private String b;

    private SuperInterface<?> c;

    private Object[] d;

    public DisablingBuilderITResult(int a) {
        this.a = a;
        this.b = de.learnlib.tooling.it.builder.DisablingBuilderIT.Defaults.b();
    }

    public String getB() {
        return this.b;
    }

    public SuperInterface<?> getC() {
        return this.c;
    }

    public DisablingBuilderITResult withC(SuperInterface<?> c) {
        this.c = c;
        return this;
    }

    public DisablingBuilderIT create(Object... d) {
        return new DisablingBuilderIT(a, b, c, d);
    }
}
