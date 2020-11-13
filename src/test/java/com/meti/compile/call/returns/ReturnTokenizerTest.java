package com.meti.compile.call.returns;

import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;
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