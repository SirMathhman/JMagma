package com.meti.compile.primitive.chars;

import com.meti.compile.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharTest {
    @Test
    void is(){
        assertTrue(new Char('x').is(Node.Group.Char));
    }

    @Test
    void render() {
        assertEquals("'x'", new Char('x').render());
    }
}