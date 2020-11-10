package com.meti.compile.generics;

import com.meti.compile.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericTypeTest {

    @Test
    void render() {
        assertEquals("void* value", new GenericType().render("value"));
    }

    @Test
    void is() {
        assertTrue(new GenericType().is(Type.Group.Generic));
    }
}