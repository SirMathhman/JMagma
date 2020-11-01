package com.meti.primitive.ints;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntTokenizerTest {

    @Test
    void tokenizePositive() {
        Assertions.assertEquals("10", new IntTokenizer("10")
                .tokenize()
                .orElseThrow()
                .render());
    }

    @Test
    void tokenizeNegative() {
        Assertions.assertEquals("-10", new IntTokenizer("-10")
                .tokenize()
                .orElseThrow()
                .render());
    }
}