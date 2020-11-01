package com.meti.ints;

import com.meti.FeatureTest;
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
