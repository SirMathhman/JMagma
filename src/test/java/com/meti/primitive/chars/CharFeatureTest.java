package com.meti.primitive.chars;

import com.meti.FeatureTest;
import org.junit.jupiter.api.Test;

class CharFeatureTest extends FeatureTest {
    @Test
    void simple(){
        assertCompile("'x'", "'x'");
    }

    @Test
    void unicode(){
        assertCompile("'我'", "'我'");
    }
}
