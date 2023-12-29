package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;
import java.lang.String;

/**
 * This is an auto-generated embedded domain-specific language for {@link AnnotationEDSLIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.AnnotationEDSLIT"
)
public class AnnotationEDSLITResult {
    private final AnnotationEDSLIT delegate;

    private AnnotationEDSLITResult0 AnnotationEDSLITResult0;

    public AnnotationEDSLITResult(AnnotationEDSLIT orig) {
        delegate = orig;
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

    public String exit() {
        return delegate.exit();
    }

    public AnnotationEDSLITResult0 ping() {
        delegate.ping();
        return getAnnotationEDSLITResult0();
    }

    public final class AnnotationEDSLITResult0 {
        private AnnotationEDSLITResult0() {
        }

        public AnnotationEDSLITResult pong() {
            delegate.pong();
            return getAnnotationEDSLITResult();
        }
    }
}
