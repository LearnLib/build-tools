package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Boolean;
import java.lang.String;

/**
 * This is an auto-generated refinement of {@link SingleRefinementIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.SingleRefinementIT"
)
public class SingleRefinementITResult<M> extends SingleRefinementIT<M, Boolean> implements SubInterface<Boolean> {
    /**
     * This is an auto-generated constructor calling {@link SingleRefinementIT#SingleRefinementIT(int, SuperInterface, String, SuperInterface2)}.
     */
    public SingleRefinementITResult(int param1, SubInterface<M> param2, String param3,
            SubInterface2<Boolean> param4) {
        super(param1, param2, param3, param4);
    }
}
