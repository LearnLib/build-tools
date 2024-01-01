package de.learnlib.tooling.processor.edsl;

import de.learnlib.tooling.annotation.Generated;
import de.learnlib.tooling.it.edsl.PackagePrivateEDSLIT;
import java.lang.String;

@Generated(
        generator = "de.learnlib.tooling.processor.edsl.EDSLProcessor",
        source = "de.learnlib.tooling.it.edsl.PackagePrivateEDSLIT"
)
class PackagePrivateEDSLITResult {
    private final PackagePrivateEDSLIT delegate;

    PackagePrivateEDSLITResult(PackagePrivateEDSLIT orig) {
        delegate = orig;
    }

    private PackagePrivateEDSLITResult getPackagePrivateEDSLITResult() {
        return this;
    }

    String done() {
        return delegate.done();
    }
}
