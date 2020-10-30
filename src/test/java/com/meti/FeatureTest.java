package com.meti;

import org.junit.jupiter.api.Assertions;

import static com.meti.MagmaCompiler.MagmaCompiler_;

public class FeatureTest {
    protected void assertCompile(String expected, String source) {
        String actual = MagmaCompiler_.compile(source);
        Assertions.assertEquals(expected, actual);
    }
}
