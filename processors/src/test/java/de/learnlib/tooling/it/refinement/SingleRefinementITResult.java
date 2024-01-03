package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Boolean;
import java.lang.String;

/**
 * A type-specific refinement of {@link SingleRefinementIT}.
 * @param <M> 
 */
@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.SingleRefinementIT"
)
public class SingleRefinementITResult<M> extends SingleRefinementIT<M, Boolean> implements SubInterface<Boolean> {
    /**
     * Delegates to {@link SingleRefinementIT#SingleRefinementIT(int, SuperInterface, String, SuperInterface2)}.
     * @param param1 maps to the {@code param1} parameter of the delegate
     * @param param2 maps to the {@code param2} parameter of the delegate
     * @param param3 maps to the {@code param3} parameter of the delegate
     * @param param4 maps to the {@code param4} parameter of the delegate
     */
    public SingleRefinementITResult(int param1, SubInterface<M> param2, String param3,
            SubInterface2<Boolean> param4) {
        super(param1, param2, param3, param4);
    }
}
