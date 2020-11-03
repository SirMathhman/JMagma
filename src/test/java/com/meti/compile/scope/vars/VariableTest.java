package com.meti.compile.scope.vars;

import com.meti.compile.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VariableTest {
    @Test
    void testEquals() {
        Node expected = new Variable("test");
        Node actual = new Variable("test");
        assertEquals(expected, actual);
    }

    @Test
    void is() {
        assertTrue(new Variable("test").is(Node.Group.Variable));
    }

    @Test
    void render() {
        assertEquals("test", new Variable("test").render());
    }

    @Test
    void mapValue() {
        assertEquals(5, new Variable("dummy").transformValue(String.class, String::length));
    }
}