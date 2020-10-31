package com.meti;

import org.junit.jupiter.api.Test;

class IfFeatureTest extends FeatureTest {
    @Test
    void simple() {
        assertCompile("if(1){}", "if(1){}");
    }
}
