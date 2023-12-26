package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.SafeVarargs;
import java.lang.String;

/**
 * This is an auto-generated refinement of {@link MultiRefinementIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.MultiRefinementIT"
)
public class MultiRefinementITResult1<M> extends MultiRefinementIT<M> {
    /**
     * This is an auto-generated constructor calling {@link MultiRefinementIT#MultiRefinementIT(int[], java.lang.Object[])}.
     */
    public MultiRefinementITResult1(int[] param1, String[] param2) {
        super(param1, param2);
    }

    /**
     * This is an auto-generated constructor calling {@link MultiRefinementIT#MultiRefinementIT(int[], java.lang.Object[], SuperInterface[])}.
     */
    @SafeVarargs
    public MultiRefinementITResult1(int[] param1, String[] param2,
            SubInterface<SuperInterface2<M>>... param3) {
        super(param1, param2, param3);
    }
}
