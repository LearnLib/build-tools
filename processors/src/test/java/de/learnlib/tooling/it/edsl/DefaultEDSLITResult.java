package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Exception;
import java.lang.Object;
import java.lang.SafeVarargs;

/**
 * A fluent interface for {@link DefaultEDSLIT}.
 * @param <I> input symbol type
 * @param <SP> state property type
 * @param <TP> transition property type
 * @param <A> automaton type
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.DefaultEDSLIT"
)
public class DefaultEDSLITResult<I, SP, TP, A> {
    private final DefaultEDSLIT<I, SP, TP, A> delegate;

    private DefaultEDSLITResult0 DefaultEDSLITResult0;

    private DefaultEDSLITResult1 DefaultEDSLITResult1;

    /**
     * Constructs a fluent interface using {@link DefaultEDSLIT#DefaultEDSLIT(Object)} to construct the object to delegate actions to.
     * @param a maps to the {@code a} parameter of the delegate
     * @throws Exception if the call to the delegate throws this exception
     */
    public DefaultEDSLITResult(A a) throws Exception {
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

    /**
     * Delegates to {@link DefaultEDSLIT#withStateProperty(Object, Object)}.
     * @param stateProperty maps to the {@code stateProperty} parameter of the delegate
     * @param stateId maps to the {@code stateId} parameter of the delegate
     * @return the next fluent state
     */
    public DefaultEDSLITResult<I, SP, TP, A> withStateProperty(SP stateProperty, Object stateId) {
        delegate.withStateProperty(stateProperty, stateId);
        return getDefaultEDSLITResult();
    }

    /**
     * Delegates to {@link DefaultEDSLIT#create()}.
     * @return the result of the delegate
     */
    public A create() {
        return delegate.create();
    }

    /**
     * Delegates to {@link DefaultEDSLIT#withInitial(Object)}.
     * @param stateId maps to the {@code stateId} parameter of the delegate
     * @return the next fluent state
     */
    public DefaultEDSLITResult<I, SP, TP, A> withInitial(Object stateId) {
        delegate.withInitial(stateId);
        return getDefaultEDSLITResult();
    }

    /**
     * Delegates to {@link DefaultEDSLIT#from(Object)}.
     * @param stateId maps to the {@code stateId} parameter of the delegate
     * @return the next fluent state
     */
    public DefaultEDSLITResult0 from(Object stateId) {
        delegate.from(stateId);
        return getDefaultEDSLITResult0();
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    @Generated(
            generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
            source = "de.learnlib.tooling.it.edsl.DefaultEDSLIT"
    )
    public final class DefaultEDSLITResult0 {
        private DefaultEDSLITResult0() {
        }

        /**
         * Delegates to {@link DefaultEDSLIT#on(Object)}.
         * @param input maps to the {@code input} parameter of the delegate
         * @throws Exception if the call to the delegate throws this exception
         * @return the next fluent state
         */
        public DefaultEDSLITResult1 on(I input) throws Exception {
            delegate.on(input);
            return getDefaultEDSLITResult1();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#on(Object, Object[])}.
         * @param firstInput maps to the {@code firstInput} parameter of the delegate
         * @param otherInputs maps to the {@code otherInputs} parameter of the delegate
         * @return the next fluent state
         */
        @SafeVarargs
        public final DefaultEDSLITResult1 on(I firstInput, I... otherInputs) {
            delegate.on(firstInput, otherInputs);
            return getDefaultEDSLITResult1();
        }
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    @Generated(
            generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
            source = "de.learnlib.tooling.it.edsl.DefaultEDSLIT"
    )
    public final class DefaultEDSLITResult1 {
        private DefaultEDSLITResult1() {
        }

        /**
         * Delegates to {@link DefaultEDSLIT#withStateProperty(Object, Object)}.
         * @param stateProperty maps to the {@code stateProperty} parameter of the delegate
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public DefaultEDSLITResult<I, SP, TP, A> withStateProperty(SP stateProperty,
                Object stateId) {
            delegate.withStateProperty(stateProperty, stateId);
            return getDefaultEDSLITResult();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#withProperty(Object)}.
         * @param transProp maps to the {@code transProp} parameter of the delegate
         * @return the next fluent state
         */
        public DefaultEDSLITResult1 withProperty(TP transProp) {
            delegate.withProperty(transProp);
            return getDefaultEDSLITResult1();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#loop()}.
         * @return the next fluent state
         */
        public DefaultEDSLITResult1 loop() {
            delegate.loop();
            return getDefaultEDSLITResult1();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#create()}.
         * @return the result of the delegate
         */
        public A create() {
            return delegate.create();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#withInitial(Object)}.
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public DefaultEDSLITResult<I, SP, TP, A> withInitial(Object stateId) {
            delegate.withInitial(stateId);
            return getDefaultEDSLITResult();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#from(Object)}.
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public DefaultEDSLITResult0 from(Object stateId) {
            delegate.from(stateId);
            return getDefaultEDSLITResult0();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#to(Object)}.
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public DefaultEDSLITResult1 to(Object stateId) {
            delegate.to(stateId);
            return getDefaultEDSLITResult1();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#on(Object)}.
         * @param input maps to the {@code input} parameter of the delegate
         * @throws Exception if the call to the delegate throws this exception
         * @return the next fluent state
         */
        public DefaultEDSLITResult1 on(I input) throws Exception {
            delegate.on(input);
            return getDefaultEDSLITResult1();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#on(Object, Object[])}.
         * @param firstInput maps to the {@code firstInput} parameter of the delegate
         * @param otherInputs maps to the {@code otherInputs} parameter of the delegate
         * @return the next fluent state
         */
        @SafeVarargs
        public final DefaultEDSLITResult1 on(I firstInput, I... otherInputs) {
            delegate.on(firstInput, otherInputs);
            return getDefaultEDSLITResult1();
        }
    }
}
