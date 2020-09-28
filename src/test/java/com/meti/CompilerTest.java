package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompilerTest {

    public static final Compiler Compiler = new Compiler();

    @Test
    void variable() {
        assertEquals("test", Compiler.compile("test"));
    }

    @Test
    void invalid() {
        assertThrows(CompileException.class, () -> Compiler.compile("temp"));
    }
}