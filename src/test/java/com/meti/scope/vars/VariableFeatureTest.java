package com.meti.scope.vars;

import com.meti.FeatureTest;
import org.junit.jupiter.api.Test;

class VariableFeatureTest extends FeatureTest {
    @Test
    void simple() {
        assertCompile("test", "test");
    }
}
