package de.learnlib.tooling.it.builder;

import de.learnlib.tooling.annotation.Generated;

/**
 * A builder for constructing {@link DocBuilderIT} instances.
 * @param <T1> the first type
 * @param <T2> the second type description
 *          that spans over multiple lines
 */
@Generated(
        generator = "de.learnlib.tooling.processor.builder.BuilderProcessor",
        source = "de.learnlib.tooling.it.builder.DocBuilderIT"
)
public final class DocBuilderITResult<T1, T2> {
    /**
     * Creates a new builder (and may set default values for some parameters).
     */
    public DocBuilderITResult() {
    }

    /**
     * Creates the {@link DocBuilderIT} instance with the configured parameters.
     * @return the created instance
     */
    public DocBuilderIT<T1, T2> create() {
        return new DocBuilderIT<T1, T2>();
    }
}
