package com.meti.initialize;

import com.meti.FeatureTest;
import org.junit.jupiter.api.Test;

class InitializationFeatureTest extends FeatureTest {
    @Test
    void simple() {
        assertCompile("int x=10;", "const x : I16 = 10");
    }
}
