package com.meti;

import org.junit.jupiter.api.Test;

class ReturnFeatureTest extends FeatureTest {
    @Test
    void simple(){
        assertCompile("return 10;", "return 10");
    }
}
