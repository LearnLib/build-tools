package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.io.IOException;
import java.lang.Boolean;
import java.lang.String;

/**
 * A type-specific refinement of {@link DocRefinementIT}.
 */
@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.DocRefinementIT"
)
public class DocRefinementIT2Result extends DocRefinementIT<String, Boolean> {
    /**
     * Delegates to {@link DocRefinementIT#DocRefinementIT()}.
     */
    public DocRefinementIT2Result() {
        super();
    }

    /**
     * Delegates to {@link DocRefinementIT#DocRefinementIT(java.lang.Object)}.
     * @param input maps to the {@code input} parameter of the delegate
     */
    public DocRefinementIT2Result(String input) {
        super(input);
    }

    /**
     * Delegates to {@link DocRefinementIT#DocRefinementIT(java.lang.Object, SuperInterface)}.
     * @param input1 maps to the {@code input1} parameter of the delegate
     * @param input2 maps to the {@code input2} parameter of the delegate
     * @throws IOException if the call to the delegate throws this exception
     */
    public DocRefinementIT2Result(String input1, SuperInterface<Boolean> input2) throws
            IOException {
        super(input1, input2);
    }
}
