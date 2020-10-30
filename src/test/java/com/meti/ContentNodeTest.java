package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContentNodeTest {

    @Test
    void is() {
        assertTrue(new ContentNode("test").is(Node.Group.Content));
    }

    @Test
    void testEquals() {
        Node expected = new ContentNode("test");
        Node actual = new ContentNode("test");
        assertEquals(expected, actual);
    }

    @Test
    void render() {
        assertThrows(IllegalStateException.class, () -> new ContentNode("test").render());
    }
}