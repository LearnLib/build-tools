package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;
import java.lang.String;

/**
 * This is an auto-generated embedded domain-specific language for {@link OverlappingEDSLIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.OverlappingEDSLIT"
)
public class OverlappingEDSLITResult {
    private final OverlappingEDSLIT delegate;

    private OverlappingEDSLITResult0 OverlappingEDSLITResult0;

    private OverlappingEDSLITResult1 OverlappingEDSLITResult1;

    private OverlappingEDSLITResult2 OverlappingEDSLITResult2;

    private OverlappingEDSLITResult3 OverlappingEDSLITResult3;

    public OverlappingEDSLITResult() {
        delegate = new OverlappingEDSLIT();
    }

    private OverlappingEDSLITResult getOverlappingEDSLITResult() {
        return this;
    }

    private OverlappingEDSLITResult0 getOverlappingEDSLITResult0() {
        if (OverlappingEDSLITResult0 == null) {
            OverlappingEDSLITResult0 = new OverlappingEDSLITResult0();
        }
        return OverlappingEDSLITResult0;
    }

    private OverlappingEDSLITResult1 getOverlappingEDSLITResult1() {
        if (OverlappingEDSLITResult1 == null) {
            OverlappingEDSLITResult1 = new OverlappingEDSLITResult1();
        }
        return OverlappingEDSLITResult1;
    }

    private OverlappingEDSLITResult2 getOverlappingEDSLITResult2() {
        if (OverlappingEDSLITResult2 == null) {
            OverlappingEDSLITResult2 = new OverlappingEDSLITResult2();
        }
        return OverlappingEDSLITResult2;
    }

    private OverlappingEDSLITResult3 getOverlappingEDSLITResult3() {
        if (OverlappingEDSLITResult3 == null) {
            OverlappingEDSLITResult3 = new OverlappingEDSLITResult3();
        }
        return OverlappingEDSLITResult3;
    }

    public OverlappingEDSLITResult0 aaa() {
        delegate.aaa();
        return getOverlappingEDSLITResult0();
    }

    public OverlappingEDSLITResult1 aa() {
        delegate.aa();
        return getOverlappingEDSLITResult1();
    }

    public String aa(String arg) {
        return delegate.aa(arg);
    }

    public final class OverlappingEDSLITResult0 {
        private OverlappingEDSLITResult0() {
        }

        public OverlappingEDSLITResult2 a() {
            delegate.a();
            return getOverlappingEDSLITResult2();
        }
    }

    public final class OverlappingEDSLITResult1 {
        private OverlappingEDSLITResult1() {
        }
    }

    public final class OverlappingEDSLITResult2 {
        private OverlappingEDSLITResult2() {
        }

        public OverlappingEDSLITResult3 aa() {
            delegate.aa();
            return getOverlappingEDSLITResult3();
        }

        public String aa(String arg) {
            return delegate.aa(arg);
        }
    }

    public final class OverlappingEDSLITResult3 {
        private OverlappingEDSLITResult3() {
        }

        public OverlappingEDSLITResult0 aaa() {
            delegate.aaa();
            return getOverlappingEDSLITResult0();
        }
    }
}
