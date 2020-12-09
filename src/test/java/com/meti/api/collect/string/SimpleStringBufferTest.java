package com.meti.api.collect.string;

import org.junit.jupiter.api.Test;

import static com.meti.api.collect.string.SimpleStringBuffer.StringBuffer;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleStringBufferTest {

	@Test
	void append() {
		assertEquals("x", StringBuffer().append('x').toString());
	}

	@Test
	void testToString() {
		assertEquals("", StringBuffer().toString());
	}
}