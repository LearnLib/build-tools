package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.SafeVarargs;
import java.lang.String;

@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.MultiRefinementIT"
)
public class MultiRefinementITResult1<M> extends MultiRefinementIT<M> {
    public MultiRefinementITResult1(int[] param1, String[] param2) {
        super(param1, param2);
    }

    @SafeVarargs
    public MultiRefinementITResult1(int[] param1, String[] param2,
            SubInterface<SuperInterface2<M>>... param3) {
        super(param1, param2, param3);
    }
}
