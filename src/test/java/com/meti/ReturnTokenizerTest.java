package com.meti;

import com.meti.content.ContentNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReturnTokenizerTest {

    @Test
    void tokenize() {
        Node expected = new Return(new ContentNode("test"));
        Node actual = new ReturnTokenizer("return test")
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }
}