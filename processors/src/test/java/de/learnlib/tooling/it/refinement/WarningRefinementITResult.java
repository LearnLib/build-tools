package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Boolean;

/**
 * A type-specific refinement of {@link WarningRefinementIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.WarningRefinementIT"
)
public class WarningRefinementITResult extends WarningRefinementIT<Boolean, SubInterface<Boolean>> {
    /**
     * Delegates to {@link WarningRefinementIT#WarningRefinementIT(SuperInterface)}.
     * @param a maps to the {@code a} parameter of the delegate
     */
    public WarningRefinementITResult(SubInterface<Boolean> a) {
        super(a);
    }
}
