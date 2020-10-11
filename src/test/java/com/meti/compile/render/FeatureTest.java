package com.meti.compile.render;

import com.meti.compile.Compiler;
import com.meti.compile.MagmaCompiler;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class FeatureTest {
    private Compiler compiler;

    @BeforeEach
    void setUp() {
        compiler = new MagmaCompiler();
    }

    protected void assertCompile(String expected, String content) {
        assertEquals(expected, compiler.compile(content));
    }

    protected void assertException(Class<? extends Exception> clazz, String content) {
        assertThrows(clazz, () -> compiler.compile(content));
    }
}
