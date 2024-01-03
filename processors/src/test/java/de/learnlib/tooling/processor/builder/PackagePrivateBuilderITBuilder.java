package de.learnlib.tooling.processor.builder;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.builder.PackagePrivateBuilderIT;
import java.lang.String;

/**
 * A builder for constructing {@link PackagePrivateBuilderIT} instances.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.builder.BuilderProcessor",
        source = "de.learnlib.tooling.it.builder.PackagePrivateBuilderIT"
)
final class PackagePrivateBuilderITBuilder {
    private String a;

    /**
     * Creates a new builder (and may set default values for some parameters).
     */
    PackagePrivateBuilderITBuilder() {
    }

    /**
     * Returns the current value for the parameter {@code a}.
     * @return the current value for the parameter {@code a}
     */
    String getA() {
        return this.a;
    }

    /**
     * Sets the new value for the parameter {@code a}.
     * @param a the new value for the parameter {@code a}
     */
    void setA(String a) {
        this.a = a;
    }

    /**
     * Sets the new value for the parameter {@code a} and returns {@code this} builder instance.
     * @param a the new value for the parameter {@code a}
     * @return the current builder instance
     */
    PackagePrivateBuilderITBuilder withA(String a) {
        this.a = a;
        return this;
    }

    /**
     * Creates the {@link PackagePrivateBuilderIT} instance with the configured parameters.
     * @return the created instance
     */
    PackagePrivateBuilderIT create() {
        return new PackagePrivateBuilderIT(a);
    }
}
