package com.meti.compile.generics;

import com.meti.compile.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericTypeTest {
    @Test
    void testEquals() {
        assertEquals(new GenericType("test"), new GenericType("test"));
    }

    @Test
    void render() {
        assertEquals("void* value", new GenericType("T").render("value"));
    }

    @Test
    void is() {
        assertTrue(new GenericType("T").is(Type.Group.Generic));
    }
}