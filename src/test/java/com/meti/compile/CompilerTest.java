package com.meti.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompilerTest {
	private static final Compiler Compiler = new Compiler();

	@Test
	void compileInt() {
		assertEquals("5", Compiler.compile("5"));
	}
}