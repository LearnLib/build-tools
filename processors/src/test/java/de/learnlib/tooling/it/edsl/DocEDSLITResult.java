package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Object;

/**
 *  A custom description for {@link DocEDSLIT}.
 *
 *  @param <SP>
 *          state property
 *  @param <A>
 *          automaton type
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.DocEDSLIT"
)
public class DocEDSLITResult<SP, A> {
    private final DocEDSLIT<SP, A> delegate;

    /**
     * Constructs a fluent interface using the provided object to delegate actions to.
     * @param delegate the object to delegate actions to
     */
    public DocEDSLITResult(DocEDSLIT<SP, A> delegate) {
        this.delegate = delegate;
    }

    private DocEDSLITResult<SP, A> getDocEDSLITResult() {
        return this;
    }

    public DocEDSLITResult<SP, A> withStateProperty(SP stateProperty, Object stateId) {
        delegate.withStateProperty(stateProperty, stateId);
        return getDocEDSLITResult();
    }

    /**
     *  Returns the final object.
     *
     *  @return the final object
     */
    public A create() {
        return delegate.create();
    }

    /**
     *  Marks the state identified by the given object as initial.
     *
     *  @param stateId
     *          the object to identify the state
     * @return the next fluent state
     */
    public DocEDSLITResult<SP, A> withInitial(Object stateId) {
        delegate.withInitial(stateId);
        return getDocEDSLITResult();
    }
}
