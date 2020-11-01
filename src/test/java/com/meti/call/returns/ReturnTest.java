package com.meti.call.returns;

import com.meti.Node;
import com.meti.scope.vars.Variable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReturnTest {
    @Test
    void testEquals() {
        Node expected = createNode();
        Node actual = createNode();
        assertEquals(expected, actual);
    }

    @Test
    void is() {
        assertTrue(createNode().is(Node.Group.Return));
    }

    private Node createNode() {
        return new Return(new Variable("test"));
    }

    @Test
    void render() {
        assertEquals("return test;", createNode().render());
    }
}