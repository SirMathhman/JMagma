package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagmaCompilerTest {
    private static final Compiler Compiler = new MagmaCompiler();

    @Test
    void function(){
        assertEquals("int main(){return 0;}", Compiler.compileToString("def main() : Int => {return 0;}"));
    }

    @Test
    void declareEmpty(){
        assertEquals("int x;", Compiler.compileToString("const x : I16"));
    }

    @Test
    void declareConstant(){
        assertEquals("int x=10;", Compiler.compileToString("const x : I16 = 10"));
    }

    @Test
    void declareVariable(){
        assertEquals("int x=10;", Compiler.compileToString("let x : I16 = 10"));
    }

    @Test
    void testFloat(){
        assertEquals("10.0", Compiler.compileToString("10.0"));
    }

    @Test
    void integer(){
        assertEquals("10", Compiler.compileToString("10"));
    }

    @Test
    void variable() {
        assertEquals("test", Compiler.compileToString("test"));
    }
}