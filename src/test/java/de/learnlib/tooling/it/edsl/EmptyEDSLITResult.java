package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;
import java.lang.String;

@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.EmptyEDSLIT"
)
public class EmptyEDSLITResult {
    private final EmptyEDSLIT delegate;

    public EmptyEDSLITResult(EmptyEDSLIT orig) {
        delegate = orig;
    }

    private EmptyEDSLITResult getEmptyEDSLITResult() {
        return this;
    }

    public String done() {
        return delegate.done();
    }
}
