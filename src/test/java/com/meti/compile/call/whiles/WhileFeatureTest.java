package com.meti.compile.call.whiles;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class WhileFeatureTest extends FeatureTest {
    @Test
    void simple() {
        assertCompile("while(1){}", "while(1){}");
    }
}
