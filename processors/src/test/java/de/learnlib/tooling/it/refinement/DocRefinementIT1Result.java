package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.io.IOException;
import java.lang.String;

/**
 * An integration test to test the behavior of document generation.
 *  <p>
 *  It contains paragraphs.
 * @param <O> output symbol type
 */
@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.DocRefinementIT"
)
public class DocRefinementIT1Result<O> extends DocRefinementIT<String, O> {
    /**
     *  This is a default constructor that uses the (Java) default values for all its internal fields.
     */
    public DocRefinementIT1Result() {
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
    public DocRefinementIT1Result(String input) {
        super(input);
    }

    /**
     *  This third constructor takes all this class' inputs as arguments.
     *
     *  @param input1
     *          this first input
     *  @param input2
     *          the second input
     *
     *  @throws IOException
     *          when things go bad
     */
    public DocRefinementIT1Result(String input1, SubInterface<O> input2) throws IOException {
        super(input1, input2);
    }
}
