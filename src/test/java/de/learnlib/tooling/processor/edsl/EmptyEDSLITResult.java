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

    private EmptyEDSLITResult0 EmptyEDSLITResult0;

    EmptyEDSLITResult(EmptyEDSLIT orig) {
        delegate = orig;
    }

    private EmptyEDSLITResult getEmptyEDSLITResult() {
        return this;
    }

    private EmptyEDSLITResult0 getEmptyEDSLITResult0() {
        if (EmptyEDSLITResult0 == null) {
            EmptyEDSLITResult0 = new EmptyEDSLITResult0();
        }
        return EmptyEDSLITResult0;
    }

    String done() {
        return delegate.done();
    }

    final class EmptyEDSLITResult0 {
        private EmptyEDSLITResult0() {
        }
    }
}
