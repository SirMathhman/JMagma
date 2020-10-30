package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VariableTest {

    @Test
    void is() {
        assertTrue(new Variable("test").is(Node.Group.Variable));
    }

    @Test
    void render() {
        assertEquals("test", new Variable("test").render());
    }
}