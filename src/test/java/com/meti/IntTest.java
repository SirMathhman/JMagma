package com.meti;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntTest {

    @Test
    void render() {
        Node node = new Int(BigInteger.TEN);
        assertEquals("10", node.render());
    }
}