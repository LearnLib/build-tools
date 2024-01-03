package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Object;
import java.lang.SafeVarargs;
import java.lang.String;

/**
 * A type-specific refinement of {@link MultiRefinementIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.MultiRefinementIT"
)
public class MultiRefinementIT2Result extends MultiRefinementIT<String[]> {
    /**
     * Delegates to {@link MultiRefinementIT#MultiRefinementIT(int[], Object[])}.
     * @param param1 maps to the {@code param1} parameter of the delegate
     * @param param2 maps to the {@code param2} parameter of the delegate
     */
    public MultiRefinementIT2Result(int[] param1, Object[] param2) {
        super(param1, param2);
    }

    /**
     * Delegates to {@link MultiRefinementIT#MultiRefinementIT(int[], Object[], SuperInterface[])}.
     * @param param1 maps to the {@code param1} parameter of the delegate
     * @param param2 maps to the {@code param2} parameter of the delegate
     * @param param3 maps to the {@code param3} parameter of the delegate
     */
    @SafeVarargs
    public MultiRefinementIT2Result(int[] param1, Object[] param2,
            SuperInterface<? extends SuperInterface2<?>>... param3) {
        super(param1, param2, param3);
    }
}
