package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Exception;
import java.lang.Object;
import java.lang.SafeVarargs;

/**
 * This is an auto-generated embedded domain-specific language for {@link DefaultEDSLIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.DefaultEDSLIT"
)
public class DefaultEDSLITResult<I, SP, TP, A> {
    private final DefaultEDSLIT<I, SP, TP, A> delegate;

    private DefaultEDSLITResult0 DefaultEDSLITResult0;

    private DefaultEDSLITResult1 DefaultEDSLITResult1;

    private DefaultEDSLITResult2 DefaultEDSLITResult2;

    public DefaultEDSLITResult(A a) {
        delegate = new DefaultEDSLIT<I, SP, TP, A>(a);
    }

    private DefaultEDSLITResult<I, SP, TP, A> getDefaultEDSLITResult() {
        return this;
    }

    private DefaultEDSLITResult0 getDefaultEDSLITResult0() {
        if (DefaultEDSLITResult0 == null) {
            DefaultEDSLITResult0 = new DefaultEDSLITResult0();
        }
        return DefaultEDSLITResult0;
    }

    private DefaultEDSLITResult1 getDefaultEDSLITResult1() {
        if (DefaultEDSLITResult1 == null) {
            DefaultEDSLITResult1 = new DefaultEDSLITResult1();
        }
        return DefaultEDSLITResult1;
    }

    private DefaultEDSLITResult2 getDefaultEDSLITResult2() {
        if (DefaultEDSLITResult2 == null) {
            DefaultEDSLITResult2 = new DefaultEDSLITResult2();
        }
        return DefaultEDSLITResult2;
    }

    public DefaultEDSLITResult<I, SP, TP, A> withStateProperty(SP stateProperty, Object stateId) {
        delegate.withStateProperty(stateProperty, stateId);
        return getDefaultEDSLITResult();
    }

    public A create() {
        return delegate.create();
    }

    public DefaultEDSLITResult<I, SP, TP, A> withInitial(Object stateId) {
        delegate.withInitial(stateId);
        return getDefaultEDSLITResult();
    }

    public DefaultEDSLITResult1 from(Object stateId) {
        delegate.from(stateId);
        return getDefaultEDSLITResult1();
    }

    public final class DefaultEDSLITResult0 {
        private DefaultEDSLITResult0() {
        }
    }

    public final class DefaultEDSLITResult1 {
        private DefaultEDSLITResult1() {
        }

        public DefaultEDSLITResult2 on(I input) throws Exception {
            delegate.on(input);
            return getDefaultEDSLITResult2();
        }

        @SafeVarargs
        public final DefaultEDSLITResult2 on(I firstInput, I... otherInputs) {
            delegate.on(firstInput, otherInputs);
            return getDefaultEDSLITResult2();
        }
    }

    public final class DefaultEDSLITResult2 {
        private DefaultEDSLITResult2() {
        }

        public DefaultEDSLITResult<I, SP, TP, A> withStateProperty(SP stateProperty,
                Object stateId) {
            delegate.withStateProperty(stateProperty, stateId);
            return getDefaultEDSLITResult();
        }

        public DefaultEDSLITResult2 withProperty(TP transProp) {
            delegate.withProperty(transProp);
            return getDefaultEDSLITResult2();
        }

        public DefaultEDSLITResult2 loop() {
            delegate.loop();
            return getDefaultEDSLITResult2();
        }

        public A create() {
            return delegate.create();
        }

        public DefaultEDSLITResult<I, SP, TP, A> withInitial(Object stateId) {
            delegate.withInitial(stateId);
            return getDefaultEDSLITResult();
        }

        public DefaultEDSLITResult1 from(Object stateId) {
            delegate.from(stateId);
            return getDefaultEDSLITResult1();
        }

        public DefaultEDSLITResult2 to(Object stateId) {
            delegate.to(stateId);
            return getDefaultEDSLITResult2();
        }

        public DefaultEDSLITResult2 on(I input) throws Exception {
            delegate.on(input);
            return getDefaultEDSLITResult2();
        }

        @SafeVarargs
        public final DefaultEDSLITResult2 on(I firstInput, I... otherInputs) {
            delegate.on(firstInput, otherInputs);
            return getDefaultEDSLITResult2();
        }
    }
}
