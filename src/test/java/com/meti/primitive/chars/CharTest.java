package com.meti.primitive.chars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharTest {

    @Test
    void render() {
        assertEquals("'x'", new Char('x').render());
    }
}