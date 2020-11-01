package com.meti.compile.primitive.chars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharTokenizerTest {

    @Test
    void tokenize() {
        assertEquals("'x'", new CharTokenizer("'x'")
                .tokenize()
                .orElseThrow()
                .render());
    }
}