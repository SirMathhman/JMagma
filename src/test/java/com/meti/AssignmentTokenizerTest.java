package com.meti;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssignmentTokenizerTest {

    @Test
    void tokenize() {
        Node expected = new Assignment(new ContentNode("x"), new ContentNode("10"));
        Node actual = new AssignmentTokenizer("x = 10")
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }
}