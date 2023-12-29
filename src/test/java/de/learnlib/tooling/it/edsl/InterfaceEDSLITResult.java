package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;

/**
 * This is an auto-generated embedded domain-specific language for {@link InterfaceEDSLIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.InterfaceEDSLIT"
)
public class InterfaceEDSLITResult {
    private final InterfaceEDSLIT delegate;

    private InterfaceEDSLITResult0 InterfaceEDSLITResult0;

    private InterfaceEDSLITResult1 InterfaceEDSLITResult1;

    public InterfaceEDSLITResult(InterfaceEDSLIT orig) {
        delegate = orig;
    }

    private InterfaceEDSLITResult getInterfaceEDSLITResult() {
        return this;
    }

    private InterfaceEDSLITResult0 getInterfaceEDSLITResult0() {
        if (InterfaceEDSLITResult0 == null) {
            InterfaceEDSLITResult0 = new InterfaceEDSLITResult0();
        }
        return InterfaceEDSLITResult0;
    }

    private InterfaceEDSLITResult1 getInterfaceEDSLITResult1() {
        if (InterfaceEDSLITResult1 == null) {
            InterfaceEDSLITResult1 = new InterfaceEDSLITResult1();
        }
        return InterfaceEDSLITResult1;
    }

    public void exit() {
        delegate.exit();
    }

    public InterfaceEDSLITResult0 ping() {
        delegate.ping();
        return getInterfaceEDSLITResult0();
    }

    public final class InterfaceEDSLITResult0 {
        private InterfaceEDSLITResult0() {
        }

        public InterfaceEDSLITResult pong() {
            delegate.pong();
            return getInterfaceEDSLITResult();
        }
    }

    public final class InterfaceEDSLITResult1 {
        private InterfaceEDSLITResult1() {
        }
    }
}
