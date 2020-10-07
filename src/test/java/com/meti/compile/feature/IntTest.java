package com.meti.compile.feature;

import org.junit.jupiter.api.Test;

class IntTest extends FeatureTest {

    @Test
    void integer() {
        assertCompile("10", "10");
    }
}