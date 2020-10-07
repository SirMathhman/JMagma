package com.meti.compile.feature.number;

import com.meti.compile.feature.FeatureTest;
import org.junit.jupiter.api.Test;

class IntTest extends FeatureTest {

    @Test
    void integer() {
        assertCompile("10", "10");
    }
}