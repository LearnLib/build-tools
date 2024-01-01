package de.learnlib.tooling.it.builder;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.refinement.SuperInterface;
import java.lang.Object;
import java.lang.String;

@Generated(
        generator = "de.learnlib.tooling.processor.builder.BuilderProcessor",
        source = "de.learnlib.tooling.it.builder.RenamingBuilderIT"
)
public final class RenamedBuilderITResult {
    private int param1;

    private String b;

    private SuperInterface<?> c;

    private Object[] d;

    public RenamedBuilderITResult() {
    }

    public int gitParam1() {
        return this.param1;
    }

    public void sitParam1(int param1) {
        this.param1 = param1;
    }

    public RenamedBuilderITResult withoutParam1(int param1) {
        this.param1 = param1;
        return this;
    }

    public String gitB() {
        return this.b;
    }

    public void sitB(String b) {
        this.b = b;
    }

    public RenamedBuilderITResult withoutB(String b) {
        this.b = b;
        return this;
    }

    public SuperInterface<?> gitccc() {
        return this.c;
    }

    public void sitccc(SuperInterface<?> c) {
        this.c = c;
    }

    public RenamedBuilderITResult withoutC(SuperInterface<?> c) {
        this.c = c;
        return this;
    }

    public Object[] gitD() {
        return this.d;
    }

    public void sitD(Object[] d) {
        this.d = d;
    }

    public RenamedBuilderITResult withoutDD(Object[] d) {
        this.d = d;
        return this;
    }

    public RenamingBuilderIT create() {
        return new RenamingBuilderIT(param1, b, c, d);
    }
}
