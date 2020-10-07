package com.meti.compile.render.scope;

import com.meti.compile.render.FeatureTest;
import org.junit.jupiter.api.Test;

public class InitializationTest extends FeatureTest {
    @Test
    void explicit() {
        assertCompile("int x=10;", "const x : I16 = 10");
    }

    @Test
    void implicitChar() {
        assertCompile("char x=10;", "const x = 10");
    }

    @Test
    void implicitInt(){
        assertCompile("int x=128;", "const x = 128");
    }
}
