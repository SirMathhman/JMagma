package com.meti.compile.feature.scope;

import com.meti.compile.feature.FeatureTest;
import org.junit.jupiter.api.Test;

public class DeclarationTest extends FeatureTest {
    @Test
    void test(){
        assertCompile("int x", "const x : I16");
    }
}
