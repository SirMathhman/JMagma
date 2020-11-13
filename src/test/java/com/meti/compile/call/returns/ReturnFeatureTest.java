package com.meti.compile.call.returns;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class ReturnFeatureTest extends FeatureTest {
    @Test
    void simple(){
        assertCompile("return 10;\n", "return 10");
    }
}
