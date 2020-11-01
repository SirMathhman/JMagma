package com.meti.compile.primitive.ints;

import com.meti.compile.Node;
import com.meti.compile.Renderable;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntTest {
    @Test
    void testEquals() {
        Node expected = new Int(BigInteger.TWO);
        Node actual = new Int(BigInteger.TWO);
        assertEquals(expected, actual);
    }

    @Test
    void render() {
        Renderable node = new Int(BigInteger.TEN);
        assertEquals("10", node.render());
    }
}