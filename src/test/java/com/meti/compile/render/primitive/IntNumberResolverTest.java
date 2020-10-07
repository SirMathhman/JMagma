package com.meti.compile.render.primitive;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static com.meti.compile.render.primitive.IntNumberResolver.Bounds8;
import static java.math.BigInteger.valueOf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntNumberResolverTest {
    @Test
    void validateBounds() {
        assertTrue(Bounds8.contains(BigInteger.ONE));
    }

    @Test
    void validateLower() {
        assertFalse(Bounds8.contains(valueOf(-128)));
    }

    @Test
    void validateUpper() {
        assertFalse(Bounds8.contains(valueOf(128)));
    }
}