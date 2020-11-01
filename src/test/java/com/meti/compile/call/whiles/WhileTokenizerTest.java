package com.meti.compile.call.whiles;

import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;
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