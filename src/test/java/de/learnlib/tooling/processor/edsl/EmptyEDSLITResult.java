package de.learnlib.tooling.processor.edsl;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.edsl.EmptyEDSLIT;
import java.lang.String;

/**
 * This is an auto-generated embedded domain-specific language for {@link EmptyEDSLIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.EmptyEDSLIT"
)
class EmptyEDSLITResult {
    private final EmptyEDSLIT delegate;

    EmptyEDSLITResult(EmptyEDSLIT orig) {
        delegate = orig;
    }

    private EmptyEDSLITResult getEmptyEDSLITResult() {
        return this;
    }

    String done() {
        return delegate.done();
    }
}
