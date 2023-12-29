package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;

/**
 * This is an auto-generated embedded domain-specific language for {@link EnumEDSLIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.EnumEDSLIT"
)
public class EnumEDSLITResult {
    private final EnumEDSLIT delegate;

    private EnumEDSLITResult0 EnumEDSLITResult0;

    public EnumEDSLITResult(EnumEDSLIT orig) {
        delegate = orig;
    }

    private EnumEDSLITResult getEnumEDSLITResult() {
        return this;
    }

    private EnumEDSLITResult0 getEnumEDSLITResult0() {
        if (EnumEDSLITResult0 == null) {
            EnumEDSLITResult0 = new EnumEDSLITResult0();
        }
        return EnumEDSLITResult0;
    }

    public void exit() {
        delegate.exit();
    }

    public EnumEDSLITResult0 ping() {
        delegate.ping();
        return getEnumEDSLITResult0();
    }

    public final class EnumEDSLITResult0 {
        private EnumEDSLITResult0() {
        }

        public EnumEDSLITResult pong() {
            delegate.pong();
            return getEnumEDSLITResult();
        }
    }
}
