package com.meti.compile;

import org.junit.jupiter.api.Test;

class IntTest extends FeatureTest {

    @Test
    void integer() {
        assertCompile("10", "10");
    }
}