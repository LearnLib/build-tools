package de.learnlib.tooling.processor.builder;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.builder.PackagePrivateBuilderIT;
import java.lang.String;

@Generated(
        generator = "de.learnlib.tooling.processor.builder.BuilderProcessor",
        source = "de.learnlib.tooling.it.builder.PackagePrivateBuilderIT"
)
final class PackagePrivateBuilderITBuilder {
    private String a;

    PackagePrivateBuilderITBuilder() {
    }

    String getA() {
        return this.a;
    }

    void setA(String a) {
        this.a = a;
    }

    PackagePrivateBuilderITBuilder withA(String a) {
        this.a = a;
        return this;
    }

    PackagePrivateBuilderIT create() {
        return new PackagePrivateBuilderIT(a);
    }
}
