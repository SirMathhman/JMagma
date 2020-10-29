package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntTokenizerTest {

    @Test
    void tokenizePositive() {
        assertEquals("10", new IntTokenizer("10")
                .tokenize()
                .orElseThrow()
                .render());
    }

    @Test
    void tokenizeNegative() {
        assertEquals("-10", new IntTokenizer("-10")
                .tokenize()
                .orElseThrow()
                .render());
    }
}