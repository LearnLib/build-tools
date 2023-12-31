package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.Boolean;
import java.lang.SafeVarargs;
import java.lang.String;
import java.util.Collection;
import java.util.function.Consumer;

@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.GenericRefinementIT"
)
public class GenericRefinementITResult extends GenericRefinementIT<String, Boolean> {
    @SafeVarargs
    public GenericRefinementITResult(Boolean a, SubInterface<String>... b) {
        super(a, b);
    }

    public GenericRefinementITResult(Collection<? extends SubInterface<String>> a, String... b) {
        super(a, b);
    }

    public GenericRefinementITResult(Consumer<? super SuperInterface<String>> a) {
        super(a);
    }
}
