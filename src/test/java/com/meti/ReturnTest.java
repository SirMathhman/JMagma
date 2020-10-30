package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReturnTest {

    @Test
    void is() {
        assertTrue(createNode().is(Node.Group.Return);
    }

    private Return createNode() {
        return new Return(new Variable("test"));
    }

    @Test
    void render() {
        assertEquals("return test;", createNode().render());
    }
}