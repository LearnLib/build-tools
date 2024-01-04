package de.learnlib.tooling.it.builder;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.refinement.SuperInterface;
import java.lang.Object;
import java.lang.String;

/**
 * A builder for constructing {@link RenamingBuilderIT} instances.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.builder.BuilderProcessor",
        source = "de.learnlib.tooling.it.builder.RenamingBuilderIT"
)
public final class RenamedBuilderITResult {
    private int param1;

    private String b;

    private SuperInterface<?> c;

    private Object[] d;

    /**
     * Creates a new builder (and may set default values for some parameters).
     */
    public RenamedBuilderITResult() {
    }

    /**
     * Returns the current value for the parameter {@code param1}.
     * @return the current value for the parameter {@code param1}
     */
    public int gitParam1() {
        return this.param1;
    }

    /**
     * Sets the new value for the parameter {@code param1}.
     * @param param1 the new value for the parameter {@code param1}
     */
    public void sitParam1(int param1) {
        this.param1 = param1;
    }

    /**
     * Sets the new value for the parameter {@code param1} and returns {@code this} builder instance.
     * @param param1 the new value for the parameter {@code param1}
     * @return the current builder instance
     */
    public RenamedBuilderITResult withoutParam1(int param1) {
        this.param1 = param1;
        return this;
    }

    /**
     * Returns the current value for the parameter {@code b}.
     * @return the current value for the parameter {@code b}
     */
    public String gitB() {
        return this.b;
    }

    /**
     * Sets the new value for the parameter {@code b}.
     * @param b the new value for the parameter {@code b}
     */
    public void sitB(String b) {
        this.b = b;
    }

    /**
     * Sets the new value for the parameter {@code b} and returns {@code this} builder instance.
     * @param b the new value for the parameter {@code b}
     * @return the current builder instance
     */
    public RenamedBuilderITResult withoutB(String b) {
        this.b = b;
        return this;
    }

    /**
     * Returns the current value for the parameter {@code c}.
     * @return the current value for the parameter {@code c}
     */
    public SuperInterface<?> gitccc() {
        return this.c;
    }

    /**
     * Sets the new value for the parameter {@code c}.
     * @param c the new value for the parameter {@code c}
     */
    public void sitccc(SuperInterface<?> c) {
        this.c = c;
    }

    /**
     * Sets the new value for the parameter {@code c} and returns {@code this} builder instance.
     * @param c the new value for the parameter {@code c}
     * @return the current builder instance
     */
    public RenamedBuilderITResult withoutC(SuperInterface<?> c) {
        this.c = c;
        return this;
    }

    /**
     * Returns the current value for the parameter {@code d}.
     * @return the current value for the parameter {@code d}
     */
    public Object[] gitD() {
        return this.d;
    }

    /**
     * Sets the new value for the parameter {@code d}.
     * @param d the new value for the parameter {@code d}
     */
    public void sitD(Object[] d) {
        this.d = d;
    }

    /**
     * Sets the new value for the parameter {@code d} and returns {@code this} builder instance.
     * @param d the new value for the parameter {@code d}
     * @return the current builder instance
     */
    public RenamedBuilderITResult withoutDD(Object[] d) {
        this.d = d;
        return this;
    }

    /**
     * Creates a new {@link RenamingBuilderIT} instance with the configured parameters.
     * @return the created instance
     */
    public RenamingBuilderIT create() {
        return new RenamingBuilderIT(param1, b, c, d);
    }
}
