package com.meti.compile.scope.initialize;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class InitializationFeatureTest extends FeatureTest {
    @Test
    void simple() {
        assertCompile("int x=10;\n", "const x : I16 = 10");
    }
}
