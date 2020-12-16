package com.meti.api.collect.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringBufferTest {
	@Test
	void add() {
		assertEquals("x", StringBuffer.Empty.add('x').asString());
	}

	@Test
	void asString(){
		assertEquals("", StringBuffer.Empty.asString());
	}
}