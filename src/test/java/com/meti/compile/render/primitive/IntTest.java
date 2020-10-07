package com.meti.compile.render.primitive;

import com.meti.compile.render.FeatureTest;
import org.junit.jupiter.api.Test;

class IntTest extends FeatureTest {

    @Test
    void integer() {
        assertCompile("10", "10");
    }
}