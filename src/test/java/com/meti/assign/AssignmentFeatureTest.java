package com.meti.assign;

import com.meti.FeatureTest;
import org.junit.jupiter.api.Test;

class AssignmentFeatureTest extends FeatureTest {
    @Test
    void simple(){
        assertCompile("x=10;", "x = 10");
    }
}
