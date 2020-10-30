package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssignmentTest {

    @Test
    void is() {
        assertTrue(createNode().is(Node.Group.Assignment));
    }

    private Node createNode() {
        Node to = new Variable("to");
        Node from = new Variable("from");
        return new Assignment(to, from);
    }

    @Test
    void render() {
        assertEquals("to=from;", createNode().render());
    }
}