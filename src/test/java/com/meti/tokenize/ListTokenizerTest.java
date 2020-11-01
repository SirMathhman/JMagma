package com.meti.tokenize;

import com.meti.chars.CharTokenizer;
import com.meti.ints.IntTokenizer;
import org.junit.jupiter.api.Test;

import static com.meti.tokenize.ListTokenizer.ListTokenizer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListTokenizerTest {
    @Test
    void tokenizeEmpty() {
        assertTrue(ListTokenizer()
                .build("test")
                .tokenize()
                .isEmpty());
    }

    @Test
    void tokenizeSingle() {
        assertEquals("10", ListTokenizer()
                .with(IntTokenizer::new)
                .build("10")
                .tokenize()
                .orElseThrow()
                .render());
    }

    @Test
    void multiple() {
        assertEquals("10", ListTokenizer()
                .with(CharTokenizer::new)
                .with(IntTokenizer::new)
                .build("10")
                .tokenize()
                .orElseThrow()
                .render());
    }
}