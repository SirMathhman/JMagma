package com.meti;

import org.junit.jupiter.api.Test;

class VariableFeatureTest extends FeatureTest {
    @Test
    void simple() {
        assertCompile("test", "test");
    }
}
