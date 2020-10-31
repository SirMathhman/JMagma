package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WhileTokenizerTest {

    @Test
    void tokenize() {
        Node expected = new While_(new ContentNode("true"), new ContentNode("{}"));
        Node actual = new WhileTokenizer("while(true){}")
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }
}