package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Object;

/**
 * A fluent interface for {@link DocEDSLIT}.
 * <p>
 * @param <SP> state property
 * @param <A> automaton type
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.DocEDSLIT"
)
public class DocEDSLITResult<SP, A> {
    private final DocEDSLIT<SP, A> delegate;

    public DocEDSLITResult(DocEDSLIT<SP, A> orig) {
        delegate = orig;
    }

    private DocEDSLITResult<SP, A> getDocEDSLITResult() {
        return this;
    }

    public DocEDSLITResult<SP, A> withStateProperty(SP stateProperty, Object stateId) {
        delegate.withStateProperty(stateProperty, stateId);
        return getDocEDSLITResult();
    }

    public A create() {
        return delegate.create();
    }

    public DocEDSLITResult<SP, A> withInitial(Object stateId) {
        delegate.withInitial(stateId);
        return getDocEDSLITResult();
    }
}
