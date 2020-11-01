package com.meti.primitive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimitiveTest {

    @Test
    void render() {
        String render = Primitive.I16.render("value");
        assertEquals("int value", render);
    }
}