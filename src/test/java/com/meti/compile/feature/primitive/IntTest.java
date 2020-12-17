package com.meti.compile.feature.primitive;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static com.meti.compile.feature.primitive.Int.Int;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntTest {

	@Test
	void render() {
		assertEquals("123456789", Int(BigInteger.valueOf(123456789)).render());
	}

	@Test
	void testEquals() {
		assertEquals(Int(10), Int(10));
	}
}