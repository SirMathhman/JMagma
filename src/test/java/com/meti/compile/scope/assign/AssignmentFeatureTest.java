package com.meti.compile.scope.assign;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class AssignmentFeatureTest extends FeatureTest {
    @Test
    void simple(){
        assertCompile("x=10;\n", "x = 10");
    }
}
