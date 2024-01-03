package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Boolean;
import java.lang.SafeVarargs;
import java.lang.String;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * A type-specific refinement of {@link GenericRefinementIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.GenericRefinementIT"
)
public class GenericRefinementITResult extends GenericRefinementIT<String, Boolean> {
    /**
     * Delegates to {@link GenericRefinementIT#GenericRefinementIT(java.lang.Object, SuperInterface[])}.
     * @param a maps to the {@code a} parameter of the delegate
     * @param b maps to the {@code b} parameter of the delegate
     */
    @SafeVarargs
    public GenericRefinementITResult(Boolean a, SubInterface<String>... b) {
        super(a, b);
    }

    /**
     * Delegates to {@link GenericRefinementIT#GenericRefinementIT(Collection, java.lang.Object[])}.
     * @param a maps to the {@code a} parameter of the delegate
     * @param b maps to the {@code b} parameter of the delegate
     */
    public GenericRefinementITResult(Collection<? extends SubInterface<String>> a, String... b) {
        super(a, b);
    }

    /**
     * Delegates to {@link GenericRefinementIT#GenericRefinementIT(Consumer)}.
     * @param a maps to the {@code a} parameter of the delegate
     */
    public GenericRefinementITResult(Consumer<? super SuperInterface<String>> a) {
        super(a);
    }
}
