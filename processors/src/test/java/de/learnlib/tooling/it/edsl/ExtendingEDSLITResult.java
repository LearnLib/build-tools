package de.learnlib.tooling.it.edsl;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.refinement.SuperInterface;
import java.lang.Exception;
import java.lang.Object;
import java.lang.SafeVarargs;

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

    public ExtendingEDSLITResult(A automaton) {
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

    public ExtendingEDSLITResult<I, A> withAccepting(Object stateId) {
        delegate.withAccepting(stateId);
        return getExtendingEDSLITResult();
    }

    public ExtendingEDSLITResult0 withInitial(Object stateId) {
        delegate.withInitial(stateId);
        return getExtendingEDSLITResult0();
    }

    public ExtendingEDSLITResult1 from(Object stateId) {
        delegate.from(stateId);
        return getExtendingEDSLITResult1();
    }

    public ExtendingEDSLITResult1 from(Object firstStateId, Object... otherStateIds) {
        delegate.from(firstStateId, otherStateIds);
        return getExtendingEDSLITResult1();
    }

    public final class ExtendingEDSLITResult0 {
        private ExtendingEDSLITResult0() {
        }

        public ExtendingEDSLITResult0 withAccepting(Object stateId) {
            delegate.withAccepting(stateId);
            return getExtendingEDSLITResult0();
        }

        public A create() {
            return delegate.create();
        }

        public ExtendingEDSLITResult2 from(Object stateId) {
            delegate.from(stateId);
            return getExtendingEDSLITResult2();
        }

        public ExtendingEDSLITResult2 from(Object firstStateId, Object... otherStateIds) {
            delegate.from(firstStateId, otherStateIds);
            return getExtendingEDSLITResult2();
        }
    }

    public final class ExtendingEDSLITResult1 {
        private ExtendingEDSLITResult1() {
        }

        public ExtendingEDSLITResult3 on(I input) throws Exception {
            delegate.on(input);
            return getExtendingEDSLITResult3();
        }

        @SafeVarargs
        public final ExtendingEDSLITResult3 on(I firstInput, I... otherInputs) {
            delegate.on(firstInput, otherInputs);
            return getExtendingEDSLITResult3();
        }
    }

    public final class ExtendingEDSLITResult2 {
        private ExtendingEDSLITResult2() {
        }

        public ExtendingEDSLITResult4 on(I input) throws Exception {
            delegate.on(input);
            return getExtendingEDSLITResult4();
        }

        @SafeVarargs
        public final ExtendingEDSLITResult4 on(I firstInput, I... otherInputs) {
            delegate.on(firstInput, otherInputs);
            return getExtendingEDSLITResult4();
        }
    }

    public final class ExtendingEDSLITResult3 {
        private ExtendingEDSLITResult3() {
        }

        public ExtendingEDSLITResult5 loop() {
            delegate.loop();
            return getExtendingEDSLITResult5();
        }

        public ExtendingEDSLITResult5 to(Object stateId) {
            delegate.to(stateId);
            return getExtendingEDSLITResult5();
        }
    }

    public final class ExtendingEDSLITResult4 {
        private ExtendingEDSLITResult4() {
        }

        public ExtendingEDSLITResult6 loop() {
            delegate.loop();
            return getExtendingEDSLITResult6();
        }

        public ExtendingEDSLITResult6 to(Object stateId) {
            delegate.to(stateId);
            return getExtendingEDSLITResult6();
        }
    }

    public final class ExtendingEDSLITResult5 {
        private ExtendingEDSLITResult5() {
        }

        public ExtendingEDSLITResult<I, A> withAccepting(Object stateId) {
            delegate.withAccepting(stateId);
            return getExtendingEDSLITResult();
        }

        public ExtendingEDSLITResult0 withInitial(Object stateId) {
            delegate.withInitial(stateId);
            return getExtendingEDSLITResult0();
        }

        public ExtendingEDSLITResult1 from(Object stateId) {
            delegate.from(stateId);
            return getExtendingEDSLITResult1();
        }

        public ExtendingEDSLITResult1 from(Object firstStateId, Object... otherStateIds) {
            delegate.from(firstStateId, otherStateIds);
            return getExtendingEDSLITResult1();
        }

        public ExtendingEDSLITResult3 on(I input) throws Exception {
            delegate.on(input);
            return getExtendingEDSLITResult3();
        }

        @SafeVarargs
        public final ExtendingEDSLITResult3 on(I firstInput, I... otherInputs) {
            delegate.on(firstInput, otherInputs);
            return getExtendingEDSLITResult3();
        }
    }

    public final class ExtendingEDSLITResult6 {
        private ExtendingEDSLITResult6() {
        }

        public ExtendingEDSLITResult0 withAccepting(Object stateId) {
            delegate.withAccepting(stateId);
            return getExtendingEDSLITResult0();
        }

        public A create() {
            return delegate.create();
        }

        public ExtendingEDSLITResult2 from(Object stateId) {
            delegate.from(stateId);
            return getExtendingEDSLITResult2();
        }

        public ExtendingEDSLITResult2 from(Object firstStateId, Object... otherStateIds) {
            delegate.from(firstStateId, otherStateIds);
            return getExtendingEDSLITResult2();
        }

        public ExtendingEDSLITResult4 on(I input) throws Exception {
            delegate.on(input);
            return getExtendingEDSLITResult4();
        }

        @SafeVarargs
        public final ExtendingEDSLITResult4 on(I firstInput, I... otherInputs) {
            delegate.on(firstInput, otherInputs);
            return getExtendingEDSLITResult4();
        }
    }
}
