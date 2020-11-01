package com.meti.compile.primitive.ints;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class IntFeatureTest extends FeatureTest {
    @Test
    void positives(){
        assertCompile("10", "10");
    }

    @Test
    void negatives(){
        assertCompile("-10", "-10");
    }
}
