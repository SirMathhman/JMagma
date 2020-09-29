package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MagmaCompilerTest {

    public static final Compiler Compiler = new MagmaCompiler();

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