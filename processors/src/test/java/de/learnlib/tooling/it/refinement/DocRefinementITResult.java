package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.lang.String;

/**
 * This is a refinement of {@link DocRefinementITResult} that binds the type variable I to {@link String} and narrows the {@link SuperInterface} parameter to {@link SubInterface}
 * @param <O> output type
 */
@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.DocRefinementIT"
)
public class DocRefinementITResult<O> extends DocRefinementIT<String, O> {
    /**
     *  This is a default constructor that uses the (Java) default values for all its internal fields.
     */
    public DocRefinementITResult() {
        super();
    }

    /**
     *  This is a second constructor stores the given input as first input and {@code null} as second.
     *
     *  @param input
     *          the input to this class
     *
     *  @see DocRefinementIT#DocRefinementIT(Object, SuperInterface)
     */
    public DocRefinementITResult(String input) {
        super(input);
    }

    /**
     *  This third constructor takes all this class' inputs as arguments.
     *
     *  @param input1
     *          this first input
     *  @param input2
     *          the second input
     */
    public DocRefinementITResult(String input1, SubInterface<O> input2) {
        super(input1, input2);
    }
}
