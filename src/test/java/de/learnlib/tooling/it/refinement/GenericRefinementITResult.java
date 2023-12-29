package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Boolean;
import java.lang.SafeVarargs;
import java.lang.String;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * This is an auto-generated refinement of {@link GenericRefinementIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.GenericRefinementIT"
)
public class GenericRefinementITResult extends GenericRefinementIT<String, Boolean> {
    /**
     * This is an auto-generated constructor calling {@link GenericRefinementIT#GenericRefinementIT(java.lang.Object, SuperInterface[])}.
     */
    @SafeVarargs
    public GenericRefinementITResult(Boolean a, SubInterface<String>... b) {
        super(a, b);
    }

    /**
     * This is an auto-generated constructor calling {@link GenericRefinementIT#GenericRefinementIT(Collection, java.lang.Object[])}.
     */
    public GenericRefinementITResult(Collection<? extends SubInterface<String>> a, String... b) {
        super(a, b);
    }

    /**
     * This is an auto-generated constructor calling {@link GenericRefinementIT#GenericRefinementIT(Consumer)}.
     */
    public GenericRefinementITResult(Consumer<? super SuperInterface<String>> a) {
        super(a);
    }
}
