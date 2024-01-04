package de.learnlib.tooling.it.builder;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.refinement.SuperInterface;
import java.lang.Object;
import java.lang.String;

/**
 * A builder for constructing {@link DisablingBuilderIT} instances.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.builder.BuilderProcessor",
        source = "de.learnlib.tooling.it.builder.DisablingBuilderIT"
)
public final class DisablingBuilderITResult {
    private int a;

    private String b;

    private SuperInterface<?> c;

    private Object[] d;

    /**
     * Creates a new builder (and may set default values for some parameters).
     * @param a the value used to initialize parameter {@code a}
     */
    public DisablingBuilderITResult(int a) {
        this.a = a;
        this.b = de.learnlib.tooling.it.builder.DisablingBuilderIT.Defaults.b();
    }

    /**
     * Returns the current value for the parameter {@code b}.
     * @return the current value for the parameter {@code b}
     */
    public String getB() {
        return this.b;
    }

    /**
     * Returns the current value for the parameter {@code c}.
     * @return the current value for the parameter {@code c}
     */
    public SuperInterface<?> getC() {
        return this.c;
    }

    /**
     * Sets the new value for the parameter {@code c} and returns {@code this} builder instance.
     * @param c the new value for the parameter {@code c}
     * @return the current builder instance
     */
    public DisablingBuilderITResult withC(SuperInterface<?> c) {
        this.c = c;
        return this;
    }

    /**
     * Creates a new {@link DisablingBuilderIT} instance with the configured parameters.
     * @param d the value used for the parameter {@code d}
     * @return the created instance
     */
    public DisablingBuilderIT create(Object... d) {
        return new DisablingBuilderIT(a, b, c, d);
    }
}
