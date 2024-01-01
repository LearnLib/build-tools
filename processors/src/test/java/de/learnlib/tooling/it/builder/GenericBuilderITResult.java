package de.learnlib.tooling.it.builder;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.refinement.SubInterface;
import de.learnlib.tooling.it.refinement.SuperInterface;
import java.lang.Exception;
import java.lang.SafeVarargs;

@Generated(
        generator = "de.learnlib.tooling.processor.builder.BuilderProcessor",
        source = "de.learnlib.tooling.it.builder.GenericBuilderIT"
)
public final class GenericBuilderITResult<I, O extends SubInterface<I>> {
    private int a;

    private I b;

    private SuperInterface<?> c;

    private O[] d;

    public GenericBuilderITResult() {
    }

    public int getA() {
        return this.a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public GenericBuilderITResult<I, O> withA(int a) {
        this.a = a;
        return this;
    }

    public I getB() {
        return this.b;
    }

    public void setB(I b) {
        this.b = b;
    }

    public GenericBuilderITResult<I, O> withB(I b) {
        this.b = b;
        return this;
    }

    public SuperInterface<?> getC() {
        return this.c;
    }

    public void setC(SuperInterface<?> c) {
        this.c = c;
    }

    public GenericBuilderITResult<I, O> withC(SuperInterface<?> c) {
        this.c = c;
        return this;
    }

    public O[] getD() {
        return this.d;
    }

    @SafeVarargs
    public final void setD(O... d) {
        this.d = d;
    }

    @SafeVarargs
    public final GenericBuilderITResult<I, O> withD(O... d) {
        this.d = d;
        return this;
    }

    @SafeVarargs
    public final GenericBuilderIT<I, O> create(O... d) throws Exception {
        return new GenericBuilderIT<I, O>(a, b, c, d);
    }
}
