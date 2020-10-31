package com.meti;

import org.junit.jupiter.api.Test;

class WhileFeatureTest extends FeatureTest {
    @Test
    void simple() {
        assertCompile("while(1){}", "while(1){}");
    }
}
