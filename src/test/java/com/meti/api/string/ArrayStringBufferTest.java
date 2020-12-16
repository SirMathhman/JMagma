package com.meti.api.string;

import org.junit.jupiter.api.Test;

import static com.meti.api.string.Allocator.JavaAllocator;
import static com.meti.api.string.ArrayStringBuffer.ArrayStringBuffer;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayStringBufferTest {

	@Test
	void add() {
		var buffer = ArrayStringBuffer(JavaAllocator);
		var bufferWithChar = buffer.add('x');
		var asString = bufferWithChar.asString();
		assertEquals("x", asString);
	}

	@Test
	void asString() {
		var value = "test";
		var asArray = value.toCharArray();
		var buffer = ArrayStringBuffer(asArray, JavaAllocator);
		var asString = buffer.asString();
		assertEquals(value, asString);
	}
}