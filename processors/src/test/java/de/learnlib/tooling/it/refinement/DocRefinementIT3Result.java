package de.learnlib.tooling.it.refinement;

import de.learnlib.tooling.annotation.Generated;
import java.io.IOException;
import java.lang.Boolean;
import java.lang.String;

@Generated(
        generator = "de.learnlib.tooling.processor.refinement.RefinementProcessor",
        source = "de.learnlib.tooling.it.refinement.DocRefinementIT"
)
public class DocRefinementIT3Result extends DocRefinementIT<String, Boolean> {
    public DocRefinementIT3Result() {
        super();
    }

    public DocRefinementIT3Result(String input) {
        super(input);
    }

    public DocRefinementIT3Result(String input1, SuperInterface<Boolean> input2) throws
            IOException {
        super(input1, input2);
    }
}
