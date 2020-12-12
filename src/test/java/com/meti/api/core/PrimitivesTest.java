package com.meti.api.core;

import org.junit.jupiter.api.Test;

import static com.meti.api.core.Primitives.valueOfInt;
import static org.junit.jupiter.api.Assertions.*;

class PrimitivesTest {

	@Test
	void valueOfIntNegative() {
		assertEquals("-55", valueOfInt(-55));
	}

	@Test
	void valueOfIntZero(){
		assertEquals("0", valueOfInt(0));
	}

	@Test
	void valueOfIntPositive(){
		assertEquals("343", valueOfInt(343));
	}
}