package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;
import java.lang.String;

/**
 * A fluent interface for {@link OverlappingEDSLIT}.
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

    /**
     * Constructs a fluent interface using {@link OverlappingEDSLIT#OverlappingEDSLIT()} to construct the object to delegate actions to.
     */
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

    /**
     * Delegates to {@link OverlappingEDSLIT#aaa()}.
     * @return the next fluent state
     */
    public OverlappingEDSLITResult0 aaa() {
        delegate.aaa();
        return getOverlappingEDSLITResult0();
    }

    /**
     * Delegates to {@link OverlappingEDSLIT#aa()}.
     * @return the next fluent state
     */
    public OverlappingEDSLITResult1 aa() {
        delegate.aa();
        return getOverlappingEDSLITResult1();
    }

    /**
     * Delegates to {@link OverlappingEDSLIT#aa(String)}.
     * @param arg maps to the {@code arg} parameter of the delegate
     * @return the result of the delegate
     */
    public String aa(String arg) {
        return delegate.aa(arg);
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    public final class OverlappingEDSLITResult0 {
        private OverlappingEDSLITResult0() {
        }

        /**
         * Delegates to {@link OverlappingEDSLIT#a()}.
         * @return the next fluent state
         */
        public OverlappingEDSLITResult2 a() {
            delegate.a();
            return getOverlappingEDSLITResult2();
        }
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    public final class OverlappingEDSLITResult1 {
        private OverlappingEDSLITResult1() {
        }
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    public final class OverlappingEDSLITResult2 {
        private OverlappingEDSLITResult2() {
        }

        /**
         * Delegates to {@link OverlappingEDSLIT#aa()}.
         * @return the next fluent state
         */
        public OverlappingEDSLITResult3 aa() {
            delegate.aa();
            return getOverlappingEDSLITResult3();
        }

        /**
         * Delegates to {@link OverlappingEDSLIT#aa(String)}.
         * @param arg maps to the {@code arg} parameter of the delegate
         * @return the result of the delegate
         */
        public String aa(String arg) {
            return delegate.aa(arg);
        }
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    public final class OverlappingEDSLITResult3 {
        private OverlappingEDSLITResult3() {
        }

        /**
         * Delegates to {@link OverlappingEDSLIT#aaa()}.
         * @return the next fluent state
         */
        public OverlappingEDSLITResult0 aaa() {
            delegate.aaa();
            return getOverlappingEDSLITResult0();
        }
    }
}
