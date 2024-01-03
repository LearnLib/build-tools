package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;

/**
 * A fluent interface for {@link EnumEDSLIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.EnumEDSLIT"
)
public class EnumEDSLITResult {
    private final EnumEDSLIT delegate;

    private EnumEDSLITResult0 EnumEDSLITResult0;

    /**
     * Constructs a fluent interface using the provided object to delegate actions to.
     * @param delegate the object to delegate actions to
     */
    public EnumEDSLITResult(EnumEDSLIT delegate) {
        this.delegate = delegate;
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

    /**
     * Delegates to {@link EnumEDSLIT#exit()}.
     */
    public void exit() {
        delegate.exit();
    }

    /**
     * Delegates to {@link EnumEDSLIT#ping()}.
     * @return the next fluent state
     */
    public EnumEDSLITResult0 ping() {
        delegate.ping();
        return getEnumEDSLITResult0();
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    public final class EnumEDSLITResult0 {
        private EnumEDSLITResult0() {
        }

        /**
         * Delegates to {@link EnumEDSLIT#pong()}.
         * @return the next fluent state
         */
        public EnumEDSLITResult pong() {
            delegate.pong();
            return getEnumEDSLITResult();
        }
    }
}
