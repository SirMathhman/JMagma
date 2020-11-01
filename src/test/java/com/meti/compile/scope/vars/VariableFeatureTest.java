package com.meti.compile.scope.vars;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class VariableFeatureTest extends FeatureTest {
    @Test
    void simple() {
        assertCompile("test", "test");
    }
}
