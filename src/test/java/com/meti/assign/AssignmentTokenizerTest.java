package com.meti.assign;

import com.meti.content.ContentNode;
import com.meti.Node;
import org.junit.jupiter.api.Test;

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