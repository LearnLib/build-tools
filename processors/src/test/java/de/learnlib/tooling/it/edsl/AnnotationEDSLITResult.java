package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;
import java.lang.String;

/**
 * A fluent interface for {@link AnnotationEDSLIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.AnnotationEDSLIT"
)
public class AnnotationEDSLITResult {
    private final AnnotationEDSLIT delegate;

    private AnnotationEDSLITResult0 AnnotationEDSLITResult0;

    /**
     * Constructs a fluent interface using the provided object to delegate actions to.
     * @param delegate the object to delegate actions to
     */
    public AnnotationEDSLITResult(AnnotationEDSLIT delegate) {
        this.delegate = delegate;
    }

    private AnnotationEDSLITResult getAnnotationEDSLITResult() {
        return this;
    }

    private AnnotationEDSLITResult0 getAnnotationEDSLITResult0() {
        if (AnnotationEDSLITResult0 == null) {
            AnnotationEDSLITResult0 = new AnnotationEDSLITResult0();
        }
        return AnnotationEDSLITResult0;
    }

    /**
     * Delegates to {@link AnnotationEDSLIT#exit()}.
     * @return the result of the delegate
     */
    public String exit() {
        return delegate.exit();
    }

    /**
     * Delegates to {@link AnnotationEDSLIT#ping()}.
     * @return the next fluent state
     */
    public AnnotationEDSLITResult0 ping() {
        delegate.ping();
        return getAnnotationEDSLITResult0();
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    public final class AnnotationEDSLITResult0 {
        private AnnotationEDSLITResult0() {
        }

        /**
         * Delegates to {@link AnnotationEDSLIT#pong()}.
         * @return the next fluent state
         */
        public AnnotationEDSLITResult pong() {
            delegate.pong();
            return getAnnotationEDSLITResult();
        }
    }
}
