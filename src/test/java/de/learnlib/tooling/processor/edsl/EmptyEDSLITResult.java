package de.learnlib.tooling.processor.edsl;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.edsl.EmptyEDSLIT;
import java.lang.String;

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
