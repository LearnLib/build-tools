package de.learnlib.tooling.processor.edsl;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.edsl.PackagePrivateEDSLIT;
import java.lang.String;

/**
 * A fluent interface for {@link PackagePrivateEDSLIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.PackagePrivateEDSLIT"
)
class PackagePrivateEDSLITResult {
    private final PackagePrivateEDSLIT delegate;

    /**
     * Constructs a fluent interface using the provided object to delegate actions to.
     * @param delegate the object to delegate actions to
     */
    PackagePrivateEDSLITResult(PackagePrivateEDSLIT delegate) {
        this.delegate = delegate;
    }

    private PackagePrivateEDSLITResult getPackagePrivateEDSLITResult() {
        return this;
    }

    /**
     * Delegates to {@link PackagePrivateEDSLIT#done()}.
     * @return the result of the delegate
     */
    String done() {
        return delegate.done();
    }
}
