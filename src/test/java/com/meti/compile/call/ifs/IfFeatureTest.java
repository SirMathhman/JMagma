package com.meti.compile.call.ifs;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class IfFeatureTest extends FeatureTest {
    @Test
    void simple() {
        assertCompile("if(1){}", "if(1){}");
    }
}
