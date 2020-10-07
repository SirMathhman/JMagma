package com.meti.compile.feature.scope;

import com.meti.compile.feature.FeatureTest;
import org.junit.jupiter.api.Test;

public class VariableTest extends FeatureTest {
    @Test
    void variable(){
        assertCompile("x", "x");
    }
}
