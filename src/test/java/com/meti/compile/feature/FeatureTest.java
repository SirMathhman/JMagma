package com.meti.compile.feature;

import com.meti.compile.Compiler;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class FeatureTest {
    private Compiler compiler;

    @BeforeEach
    void setUp(){
        compiler = new Compiler();
    }

    protected void assertCompile(String expected, String content) {
        assertEquals(expected, compiler.compile(content));
    }
}
