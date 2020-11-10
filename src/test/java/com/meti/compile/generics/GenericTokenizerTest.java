package com.meti.compile.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericTokenizerTest {
    @Test
    void simple() {
        assertEquals("void* test", new GenericTokenizer("T")
                .tokenize()
                .orElseThrow()
                .render("test"));
    }
}