package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Boolean;
import java.lang.String;

@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.SingleRefinementIT"
)
public class SingleRefinementITResult<M> extends SingleRefinementIT<M, Boolean> implements SubInterface<Boolean> {
    public SingleRefinementITResult(int param1, SubInterface<M> param2, String param3,
            SubInterface2<Boolean> param4) {
        super(param1, param2, param3, param4);
    }
}
