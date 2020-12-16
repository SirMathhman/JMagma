package com.meti.api.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathTest {
	@Test
	void maxIntFirst() {
		assertEquals(3, Math.maxInt(3, 1));
	}

	@Test
	void maxIntEquals() {
		assertEquals(3, Math.maxInt(3, 3));
	}

	@Test
	void maxIntSecond() {
		assertEquals(3, Math.maxInt(1, 3));
	}
}