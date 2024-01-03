package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.SafeVarargs;
import java.lang.String;

/**
 * A type-specific refinement of {@link MultiRefinementIT}.
 * @param <M> 
 */
@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.MultiRefinementIT"
)
public class MultiRefinementIT1Result<M> extends MultiRefinementIT<M> {
    /**
     * Delegates to {@link MultiRefinementIT#MultiRefinementIT(int[], java.lang.Object[])}.
     * @param param1 maps to the {@code param1} parameter of the delegate
     * @param param2 maps to the {@code param2} parameter of the delegate
     */
    public MultiRefinementIT1Result(int[] param1, String[] param2) {
        super(param1, param2);
    }

    /**
     * Delegates to {@link MultiRefinementIT#MultiRefinementIT(int[], java.lang.Object[], SuperInterface[])}.
     * @param param1 maps to the {@code param1} parameter of the delegate
     * @param param2 maps to the {@code param2} parameter of the delegate
     * @param param3 maps to the {@code param3} parameter of the delegate
     */
    @SafeVarargs
    public MultiRefinementIT1Result(int[] param1, String[] param2,
            SubInterface<SuperInterface2<M>>... param3) {
        super(param1, param2, param3);
    }
}
