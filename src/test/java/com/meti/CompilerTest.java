package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompilerTest {

    public static final Compiler Compiler = new Compiler();

    @Test
    void integer(){
        assertEquals("10", Compiler.compileToString("10"));
    }

    @Test
    void variable() {
        assertEquals("test", Compiler.compileToString("test"));
    }
}