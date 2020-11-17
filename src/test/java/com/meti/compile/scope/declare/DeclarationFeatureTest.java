package com.meti.compile.scope.declare;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class DeclarationFeatureTest  extends FeatureTest {


    @Test
    void immutable(){
        assertCompile("int x;\n", "const x : I16");
    }

    @Test
    void mutable(){
        assertCompile("int x;\n", "let x : I16");
    }
}
