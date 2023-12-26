package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Object;
import java.lang.SafeVarargs;
import java.lang.String;

/**
 * This is an auto-generated refinement of {@link MultiRefinementIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.MultiRefinementIT"
)
public class MultiRefinementITResult2 extends MultiRefinementIT<String[]> {
    /**
     * This is an auto-generated constructor calling {@link MultiRefinementIT#MultiRefinementIT(int[], Object[])}.
     */
    public MultiRefinementITResult2(int[] param1, Object[] param2) {
        super(param1, param2);
    }

    /**
     * This is an auto-generated constructor calling {@link MultiRefinementIT#MultiRefinementIT(int[], Object[], SuperInterface[])}.
     */
    @SafeVarargs
    public MultiRefinementITResult2(int[] param1, Object[] param2,
            SuperInterface<? extends SuperInterface2<?>>... param3) {
        super(param1, param2, param3);
    }
}
