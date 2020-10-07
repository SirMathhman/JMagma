package com.meti.compile.render.scope;

import com.meti.compile.render.FeatureTest;
import org.junit.jupiter.api.Test;

public class DeclarationTest extends FeatureTest {
    @Test
    void test(){
        assertCompile("int x;", "const x : I16");
    }
}
