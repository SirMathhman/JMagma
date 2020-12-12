package com.meti.api.core;

import org.junit.jupiter.api.Test;

import static com.meti.api.core.Primitives.isDigit;
import static org.junit.jupiter.api.Assertions.*;

class PrimitivesTest {
	@Test
	void isDigitInvalidLeft() {
		assertFalse(isDigit('\0'));
	}

	@Test
	void asDigitValid() throws FormatException {
		assertEquals(0, Primitives.asDigit('0'));
	}

	@Test
	void assertDigitInvalid() {
		assertThrows(FormatException.class, () -> Primitives.asDigit('x'));
	}

	@Test
	void isDigitInvalidRight() {
		assertFalse(isDigit('%'));
	}

	@Test
	void isDigitValid() {
		assertTrue(isDigit('3'));
	}
}