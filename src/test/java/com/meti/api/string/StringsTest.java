package com.meti.api.string;

import org.junit.jupiter.api.Test;

import static com.meti.api.memory.Allocator.JavaAllocator;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StringsTest {

	@Test
	void fromInt() {
		var actual = Strings.fromInt(0100, 8, JavaAllocator);
		assertEquals("100", actual);
	}

	@Test
	void fromIntNegative() {
		var actual = Strings.fromInt(-55, JavaAllocator);
		assertEquals("-55", actual);
	}

	@Test
	void fromIntDigit(){
		var actual = Strings.fromInt(5, JavaAllocator);
		assertEquals("5", actual);
	}
}