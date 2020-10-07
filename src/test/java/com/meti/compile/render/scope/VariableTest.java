package com.meti.compile.render.scope;

import com.meti.compile.render.FeatureTest;
import org.junit.jupiter.api.Test;

public class VariableTest extends FeatureTest {
    @Test
    void variable(){
        assertCompile("x", "x");
    }
}
