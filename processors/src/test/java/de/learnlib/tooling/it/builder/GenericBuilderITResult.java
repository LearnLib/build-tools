package de.learnlib.tooling.it.builder;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.refinement.SubInterface;
import de.learnlib.tooling.it.refinement.SuperInterface;
import java.lang.Exception;
import java.lang.SafeVarargs;

/**
 * A builder for constructing {@link GenericBuilderIT} instances.
 * @param <I> input type
 * @param <O> output type
 */
@Generated(
        generator = "de.learnlib.tooling.processor.builder.BuilderProcessor",
        source = "de.learnlib.tooling.it.builder.GenericBuilderIT"
)
public final class GenericBuilderITResult<I, O extends SubInterface<I>> {
    private int a;

    private I b;

    private SuperInterface<?> c;

    private O[] d;

    /**
     * Creates a new builder (and may set default values for some parameters).
     */
    public GenericBuilderITResult() {
    }

    /**
     * Returns the current value for the parameter {@code a}.
     * @return the current value for the parameter {@code a}
     */
    public int getA() {
        return this.a;
    }

    /**
     * Sets the new value for the parameter {@code a}.
     * @param a the new value for the parameter {@code a}
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * Sets the new value for the parameter {@code a} and returns {@code this} builder instance.
     * @param a the new value for the parameter {@code a}
     * @return the current builder instance
     */
    public GenericBuilderITResult<I, O> withA(int a) {
        this.a = a;
        return this;
    }

    /**
     * Returns the current value for the parameter {@code b}.
     * @return the current value for the parameter {@code b}
     */
    public I getB() {
        return this.b;
    }

    /**
     * Sets the new value for the parameter {@code b}.
     * @param b the new value for the parameter {@code b}
     */
    public void setB(I b) {
        this.b = b;
    }

    /**
     * Sets the new value for the parameter {@code b} and returns {@code this} builder instance.
     * @param b the new value for the parameter {@code b}
     * @return the current builder instance
     */
    public GenericBuilderITResult<I, O> withB(I b) {
        this.b = b;
        return this;
    }

    /**
     * Returns the current value for the parameter {@code c}.
     * @return the current value for the parameter {@code c}
     */
    public SuperInterface<?> getC() {
        return this.c;
    }

    /**
     * Sets the new value for the parameter {@code c}.
     * @param c the new value for the parameter {@code c}
     */
    public void setC(SuperInterface<?> c) {
        this.c = c;
    }

    /**
     * Sets the new value for the parameter {@code c} and returns {@code this} builder instance.
     * @param c the new value for the parameter {@code c}
     * @return the current builder instance
     */
    public GenericBuilderITResult<I, O> withC(SuperInterface<?> c) {
        this.c = c;
        return this;
    }

    /**
     * Returns the current value for the parameter {@code d}.
     * @return the current value for the parameter {@code d}
     */
    public O[] getD() {
        return this.d;
    }

    /**
     * Sets the new value for the parameter {@code d}.
     * @param d the new value for the parameter {@code d}
     */
    @SafeVarargs
    public final void setD(O... d) {
        this.d = d;
    }

    /**
     * Sets the new value for the parameter {@code d} and returns {@code this} builder instance.
     * @param d the new value for the parameter {@code d}
     * @return the current builder instance
     */
    @SafeVarargs
    public final GenericBuilderITResult<I, O> withD(O... d) {
        this.d = d;
        return this;
    }

    /**
     * Creates the {@link GenericBuilderIT} instance with the configured parameters.
     * @param d the value used for the parameter {@code d}
     * @return the created instance
     * @throws Exception if instantiating the object throws this exception
     */
    @SafeVarargs
    public final GenericBuilderIT<I, O> create(O... d) throws Exception {
        return new GenericBuilderIT<I, O>(a, b, c, d);
    }
}
