package com.meti.compile.render.function;

import com.meti.compile.render.FeatureTest;
import org.junit.jupiter.api.Test;

public class ReturnTest extends FeatureTest {
    @Test
    void test(){
        assertCompile("return 10;", "return 10");
    }
}
