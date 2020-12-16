package com.meti.api.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitivesTest {

	@Test
	void asAlphaNumeric() {
		assertEquals('5', Primitives.asAlphaNumeric(5));
	}

	@Test
	void asAlphaNumericLetter(){
		assertEquals('A', Primitives.asAlphaNumeric(10));
	}

	@Test
	void asAlphaNumericWrapper(){
		assertEquals('0', Primitives.asAlphaNumeric(36));
	}
}