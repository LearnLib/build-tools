package de.learnlib.tooling.it.builder;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.refinement.SuperInterface;
import java.lang.String;

/**
 * A builder for constructing {@link SimpleBuilderIT} instances.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.builder.BuilderProcessor",
        source = "de.learnlib.tooling.it.builder.SimpleBuilderIT"
)
public final class SimpleBuilderITResult {
    private int a;

    private String b;

    private SuperInterface<?> c;

    private boolean[] d;

    /**
     * Creates a new builder (and may set default values for some parameters).
     */
    public SimpleBuilderITResult() {
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
    public SimpleBuilderITResult withA(int a) {
        this.a = a;
        return this;
    }

    /**
     * Returns the current value for the parameter {@code b}.
     * @return the current value for the parameter {@code b}
     */
    public String getB() {
        return this.b;
    }

    /**
     * Sets the new value for the parameter {@code b}.
     * @param b the new value for the parameter {@code b}
     */
    public void setB(String b) {
        this.b = b;
    }

    /**
     * Sets the new value for the parameter {@code b} and returns {@code this} builder instance.
     * @param b the new value for the parameter {@code b}
     * @return the current builder instance
     */
    public SimpleBuilderITResult withB(String b) {
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
    public SimpleBuilderITResult withC(SuperInterface<?> c) {
        this.c = c;
        return this;
    }

    /**
     * Returns the current value for the parameter {@code d}.
     * @return the current value for the parameter {@code d}
     */
    public boolean[] getD() {
        return this.d;
    }

    /**
     * Sets the new value for the parameter {@code d}.
     * @param d the new value for the parameter {@code d}
     */
    public void setD(boolean... d) {
        this.d = d;
    }

    /**
     * Sets the new value for the parameter {@code d} and returns {@code this} builder instance.
     * @param d the new value for the parameter {@code d}
     * @return the current builder instance
     */
    public SimpleBuilderITResult withD(boolean... d) {
        this.d = d;
        return this;
    }

    /**
     * Creates the {@link SimpleBuilderIT} instance with the configured parameters.
     * @return the created instance
     */
    public SimpleBuilderIT create() {
        return new SimpleBuilderIT(a, b, c, d);
    }
}
