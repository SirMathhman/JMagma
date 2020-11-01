package com.meti.call.ifs;

import com.meti.FeatureTest;
import org.junit.jupiter.api.Test;

class IfFeatureTest extends FeatureTest {
    @Test
    void simple() {
        assertCompile("if(1){}", "if(1){}");
    }
}
