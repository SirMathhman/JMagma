package com.meti.compile.render.block.function;

import com.meti.compile.render.FeatureTest;
import org.junit.jupiter.api.Test;

public class ReturnFeatureTest extends FeatureTest {
    @Test
    void test() {
        assertCompile("return 10;", "return 10");
    }
}
