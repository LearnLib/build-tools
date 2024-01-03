package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;

/**
 * A fluent interface for {@link InterfaceEDSLIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.InterfaceEDSLIT"
)
public class InterfaceEDSLITResult {
    private final InterfaceEDSLIT delegate;

    private InterfaceEDSLITResult0 InterfaceEDSLITResult0;

    /**
     * Constructs a fluent interface using the provided object to delegate actions to.
     * @param delegate the object to delegate actions to
     */
    public InterfaceEDSLITResult(InterfaceEDSLIT delegate) {
        this.delegate = delegate;
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

    /**
     * Delegates to {@link InterfaceEDSLIT#exit()}.
     */
    public void exit() {
        delegate.exit();
    }

    /**
     * Delegates to {@link InterfaceEDSLIT#ping()}.
     * @return the next fluent state
     */
    public InterfaceEDSLITResult0 ping() {
        delegate.ping();
        return getInterfaceEDSLITResult0();
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    public final class InterfaceEDSLITResult0 {
        private InterfaceEDSLITResult0() {
        }

        /**
         * Delegates to {@link InterfaceEDSLIT#pong()}.
         * @return the next fluent state
         */
        public InterfaceEDSLITResult pong() {
            delegate.pong();
            return getInterfaceEDSLITResult();
        }
    }
}
