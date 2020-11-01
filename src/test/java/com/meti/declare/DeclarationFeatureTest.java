package com.meti.declare;

import com.meti.FeatureTest;
import org.junit.jupiter.api.Test;

class DeclarationFeatureTest  extends FeatureTest {
    @Test
    void immutable(){
        assertCompile("int x;", "const x : I16");
    }

    @Test
    void mutable(){
        assertCompile("int x;", "let x : I16");
    }
}
