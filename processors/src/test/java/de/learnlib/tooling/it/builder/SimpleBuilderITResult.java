package de.learnlib.tooling.it.builder;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.refinement.SuperInterface;
import java.lang.String;

@Generated(
        generator = "de.learnlib.tooling.processor.builder.BuilderProcessor",
        source = "de.learnlib.tooling.it.builder.SimpleBuilderIT"
)
public final class SimpleBuilderITResult {
    private int a;

    private String b;

    private SuperInterface<?> c;

    private boolean[] d;

    public SimpleBuilderITResult() {
    }

    public int getA() {
        return this.a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public SimpleBuilderITResult withA(int a) {
        this.a = a;
        return this;
    }

    public String getB() {
        return this.b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public SimpleBuilderITResult withB(String b) {
        this.b = b;
        return this;
    }

    public SuperInterface<?> getC() {
        return this.c;
    }

    public void setC(SuperInterface<?> c) {
        this.c = c;
    }

    public SimpleBuilderITResult withC(SuperInterface<?> c) {
        this.c = c;
        return this;
    }

    public boolean[] getD() {
        return this.d;
    }

    public void setD(boolean... d) {
        this.d = d;
    }

    public SimpleBuilderITResult withD(boolean... d) {
        this.d = d;
        return this;
    }

    public SimpleBuilderIT create() {
        return new SimpleBuilderIT(a, b, c, d);
    }
}
