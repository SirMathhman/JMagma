package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimitiveTokenizerTest {

    @Test
    void tokenizeValid() {
        assertEquals("int x", new PrimitiveTokenizer("I16")
                .tokenize()
                .orElseThrow()
                .render("x"));
    }

    @Test
    void tokenizeInvalid(){
        assertTrue(new PrimitiveTokenizer("test").tokenize().isEmpty());
    }
}