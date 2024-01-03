package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.refinement.SuperInterface;
import java.lang.Exception;
import java.lang.Object;
import java.lang.SafeVarargs;

/**
 * A fluent interface for {@link ExtendingEDSLIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.ExtendingEDSLIT"
)
public class ExtendingEDSLITResult<I, A extends SuperInterface<?>> {
    private final ExtendingEDSLIT<I, A> delegate;

    private ExtendingEDSLITResult0 ExtendingEDSLITResult0;

    private ExtendingEDSLITResult1 ExtendingEDSLITResult1;

    private ExtendingEDSLITResult2 ExtendingEDSLITResult2;

    private ExtendingEDSLITResult3 ExtendingEDSLITResult3;

    private ExtendingEDSLITResult4 ExtendingEDSLITResult4;

    private ExtendingEDSLITResult5 ExtendingEDSLITResult5;

    private ExtendingEDSLITResult6 ExtendingEDSLITResult6;

    /**
     * Constructs a fluent interface using {@link ExtendingEDSLIT#ExtendingEDSLIT(SuperInterface)} to construct the object to delegate actions to.
     * @param automaton maps to the {@code automaton} parameter of the delegate
     * @throws Exception if the call to the delegate throws this exception
     */
    public ExtendingEDSLITResult(A automaton) throws Exception {
        delegate = new ExtendingEDSLIT<I, A>(automaton);
    }

    private ExtendingEDSLITResult<I, A> getExtendingEDSLITResult() {
        return this;
    }

    private ExtendingEDSLITResult0 getExtendingEDSLITResult0() {
        if (ExtendingEDSLITResult0 == null) {
            ExtendingEDSLITResult0 = new ExtendingEDSLITResult0();
        }
        return ExtendingEDSLITResult0;
    }

    private ExtendingEDSLITResult1 getExtendingEDSLITResult1() {
        if (ExtendingEDSLITResult1 == null) {
            ExtendingEDSLITResult1 = new ExtendingEDSLITResult1();
        }
        return ExtendingEDSLITResult1;
    }

    private ExtendingEDSLITResult2 getExtendingEDSLITResult2() {
        if (ExtendingEDSLITResult2 == null) {
            ExtendingEDSLITResult2 = new ExtendingEDSLITResult2();
        }
        return ExtendingEDSLITResult2;
    }

    private ExtendingEDSLITResult3 getExtendingEDSLITResult3() {
        if (ExtendingEDSLITResult3 == null) {
            ExtendingEDSLITResult3 = new ExtendingEDSLITResult3();
        }
        return ExtendingEDSLITResult3;
    }

    private ExtendingEDSLITResult4 getExtendingEDSLITResult4() {
        if (ExtendingEDSLITResult4 == null) {
            ExtendingEDSLITResult4 = new ExtendingEDSLITResult4();
        }
        return ExtendingEDSLITResult4;
    }

    private ExtendingEDSLITResult5 getExtendingEDSLITResult5() {
        if (ExtendingEDSLITResult5 == null) {
            ExtendingEDSLITResult5 = new ExtendingEDSLITResult5();
        }
        return ExtendingEDSLITResult5;
    }

    private ExtendingEDSLITResult6 getExtendingEDSLITResult6() {
        if (ExtendingEDSLITResult6 == null) {
            ExtendingEDSLITResult6 = new ExtendingEDSLITResult6();
        }
        return ExtendingEDSLITResult6;
    }

    /**
     * Delegates to {@link ExtendingEDSLIT#withAccepting(Object)}.
     * @param stateId maps to the {@code stateId} parameter of the delegate
     * @return the next fluent state
     */
    public ExtendingEDSLITResult<I, A> withAccepting(Object stateId) {
        delegate.withAccepting(stateId);
        return getExtendingEDSLITResult();
    }

    /**
     * Delegates to {@link ExtendingEDSLIT#withInitial(Object)}.
     * @param stateId maps to the {@code stateId} parameter of the delegate
     * @return the next fluent state
     */
    public ExtendingEDSLITResult0 withInitial(Object stateId) {
        delegate.withInitial(stateId);
        return getExtendingEDSLITResult0();
    }

    /**
     * Delegates to {@link DefaultEDSLIT#from(Object)}.
     * @param stateId maps to the {@code stateId} parameter of the delegate
     * @return the next fluent state
     */
    public ExtendingEDSLITResult1 from(Object stateId) {
        delegate.from(stateId);
        return getExtendingEDSLITResult1();
    }

    /**
     * Delegates to {@link ExtendingEDSLIT#from(Object, Object[])}.
     * @param firstStateId maps to the {@code firstStateId} parameter of the delegate
     * @param otherStateIds maps to the {@code otherStateIds} parameter of the delegate
     * @return the next fluent state
     */
    public ExtendingEDSLITResult1 from(Object firstStateId, Object... otherStateIds) {
        delegate.from(firstStateId, otherStateIds);
        return getExtendingEDSLITResult1();
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    @Generated(
            generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
            source = "de.learnlib.tooling.it.edsl.ExtendingEDSLIT"
    )
    public final class ExtendingEDSLITResult0 {
        private ExtendingEDSLITResult0() {
        }

        /**
         * Delegates to {@link ExtendingEDSLIT#withAccepting(Object)}.
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public ExtendingEDSLITResult0 withAccepting(Object stateId) {
            delegate.withAccepting(stateId);
            return getExtendingEDSLITResult0();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#create()}.
         * @return the result of the delegate
         */
        public A create() {
            return delegate.create();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#from(Object)}.
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public ExtendingEDSLITResult2 from(Object stateId) {
            delegate.from(stateId);
            return getExtendingEDSLITResult2();
        }

        /**
         * Delegates to {@link ExtendingEDSLIT#from(Object, Object[])}.
         * @param firstStateId maps to the {@code firstStateId} parameter of the delegate
         * @param otherStateIds maps to the {@code otherStateIds} parameter of the delegate
         * @return the next fluent state
         */
        public ExtendingEDSLITResult2 from(Object firstStateId, Object... otherStateIds) {
            delegate.from(firstStateId, otherStateIds);
            return getExtendingEDSLITResult2();
        }
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    @Generated(
            generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
            source = "de.learnlib.tooling.it.edsl.ExtendingEDSLIT"
    )
    public final class ExtendingEDSLITResult1 {
        private ExtendingEDSLITResult1() {
        }

        /**
         * Delegates to {@link DefaultEDSLIT#on(Object)}.
         * @param input maps to the {@code input} parameter of the delegate
         * @throws Exception if the call to the delegate throws this exception
         * @return the next fluent state
         */
        public ExtendingEDSLITResult3 on(I input) throws Exception {
            delegate.on(input);
            return getExtendingEDSLITResult3();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#on(Object, Object[])}.
         * @param firstInput maps to the {@code firstInput} parameter of the delegate
         * @param otherInputs maps to the {@code otherInputs} parameter of the delegate
         * @return the next fluent state
         */
        @SafeVarargs
        public final ExtendingEDSLITResult3 on(I firstInput, I... otherInputs) {
            delegate.on(firstInput, otherInputs);
            return getExtendingEDSLITResult3();
        }
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    @Generated(
            generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
            source = "de.learnlib.tooling.it.edsl.ExtendingEDSLIT"
    )
    public final class ExtendingEDSLITResult2 {
        private ExtendingEDSLITResult2() {
        }

        /**
         * Delegates to {@link DefaultEDSLIT#on(Object)}.
         * @param input maps to the {@code input} parameter of the delegate
         * @throws Exception if the call to the delegate throws this exception
         * @return the next fluent state
         */
        public ExtendingEDSLITResult4 on(I input) throws Exception {
            delegate.on(input);
            return getExtendingEDSLITResult4();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#on(Object, Object[])}.
         * @param firstInput maps to the {@code firstInput} parameter of the delegate
         * @param otherInputs maps to the {@code otherInputs} parameter of the delegate
         * @return the next fluent state
         */
        @SafeVarargs
        public final ExtendingEDSLITResult4 on(I firstInput, I... otherInputs) {
            delegate.on(firstInput, otherInputs);
            return getExtendingEDSLITResult4();
        }
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    @Generated(
            generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
            source = "de.learnlib.tooling.it.edsl.ExtendingEDSLIT"
    )
    public final class ExtendingEDSLITResult3 {
        private ExtendingEDSLITResult3() {
        }

        /**
         * Delegates to {@link DefaultEDSLIT#loop()}.
         * @return the next fluent state
         */
        public ExtendingEDSLITResult5 loop() {
            delegate.loop();
            return getExtendingEDSLITResult5();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#to(Object)}.
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public ExtendingEDSLITResult5 to(Object stateId) {
            delegate.to(stateId);
            return getExtendingEDSLITResult5();
        }
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    @Generated(
            generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
            source = "de.learnlib.tooling.it.edsl.ExtendingEDSLIT"
    )
    public final class ExtendingEDSLITResult4 {
        private ExtendingEDSLITResult4() {
        }

        /**
         * Delegates to {@link DefaultEDSLIT#loop()}.
         * @return the next fluent state
         */
        public ExtendingEDSLITResult6 loop() {
            delegate.loop();
            return getExtendingEDSLITResult6();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#to(Object)}.
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public ExtendingEDSLITResult6 to(Object stateId) {
            delegate.to(stateId);
            return getExtendingEDSLITResult6();
        }
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    @Generated(
            generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
            source = "de.learnlib.tooling.it.edsl.ExtendingEDSLIT"
    )
    public final class ExtendingEDSLITResult5 {
        private ExtendingEDSLITResult5() {
        }

        /**
         * Delegates to {@link ExtendingEDSLIT#withAccepting(Object)}.
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public ExtendingEDSLITResult<I, A> withAccepting(Object stateId) {
            delegate.withAccepting(stateId);
            return getExtendingEDSLITResult();
        }

        /**
         * Delegates to {@link ExtendingEDSLIT#withInitial(Object)}.
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public ExtendingEDSLITResult0 withInitial(Object stateId) {
            delegate.withInitial(stateId);
            return getExtendingEDSLITResult0();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#from(Object)}.
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public ExtendingEDSLITResult1 from(Object stateId) {
            delegate.from(stateId);
            return getExtendingEDSLITResult1();
        }

        /**
         * Delegates to {@link ExtendingEDSLIT#from(Object, Object[])}.
         * @param firstStateId maps to the {@code firstStateId} parameter of the delegate
         * @param otherStateIds maps to the {@code otherStateIds} parameter of the delegate
         * @return the next fluent state
         */
        public ExtendingEDSLITResult1 from(Object firstStateId, Object... otherStateIds) {
            delegate.from(firstStateId, otherStateIds);
            return getExtendingEDSLITResult1();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#on(Object)}.
         * @param input maps to the {@code input} parameter of the delegate
         * @throws Exception if the call to the delegate throws this exception
         * @return the next fluent state
         */
        public ExtendingEDSLITResult3 on(I input) throws Exception {
            delegate.on(input);
            return getExtendingEDSLITResult3();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#on(Object, Object[])}.
         * @param firstInput maps to the {@code firstInput} parameter of the delegate
         * @param otherInputs maps to the {@code otherInputs} parameter of the delegate
         * @return the next fluent state
         */
        @SafeVarargs
        public final ExtendingEDSLITResult3 on(I firstInput, I... otherInputs) {
            delegate.on(firstInput, otherInputs);
            return getExtendingEDSLITResult3();
        }
    }

    /**
     * A state (-class) of the enclosing fluent interface.
     */
    @Generated(
            generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
            source = "de.learnlib.tooling.it.edsl.ExtendingEDSLIT"
    )
    public final class ExtendingEDSLITResult6 {
        private ExtendingEDSLITResult6() {
        }

        /**
         * Delegates to {@link ExtendingEDSLIT#withAccepting(Object)}.
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public ExtendingEDSLITResult0 withAccepting(Object stateId) {
            delegate.withAccepting(stateId);
            return getExtendingEDSLITResult0();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#create()}.
         * @return the result of the delegate
         */
        public A create() {
            return delegate.create();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#from(Object)}.
         * @param stateId maps to the {@code stateId} parameter of the delegate
         * @return the next fluent state
         */
        public ExtendingEDSLITResult2 from(Object stateId) {
            delegate.from(stateId);
            return getExtendingEDSLITResult2();
        }

        /**
         * Delegates to {@link ExtendingEDSLIT#from(Object, Object[])}.
         * @param firstStateId maps to the {@code firstStateId} parameter of the delegate
         * @param otherStateIds maps to the {@code otherStateIds} parameter of the delegate
         * @return the next fluent state
         */
        public ExtendingEDSLITResult2 from(Object firstStateId, Object... otherStateIds) {
            delegate.from(firstStateId, otherStateIds);
            return getExtendingEDSLITResult2();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#on(Object)}.
         * @param input maps to the {@code input} parameter of the delegate
         * @throws Exception if the call to the delegate throws this exception
         * @return the next fluent state
         */
        public ExtendingEDSLITResult4 on(I input) throws Exception {
            delegate.on(input);
            return getExtendingEDSLITResult4();
        }

        /**
         * Delegates to {@link DefaultEDSLIT#on(Object, Object[])}.
         * @param firstInput maps to the {@code firstInput} parameter of the delegate
         * @param otherInputs maps to the {@code otherInputs} parameter of the delegate
         * @return the next fluent state
         */
        @SafeVarargs
        public final ExtendingEDSLITResult4 on(I firstInput, I... otherInputs) {
            delegate.on(firstInput, otherInputs);
            return getExtendingEDSLITResult4();
        }
    }
}
