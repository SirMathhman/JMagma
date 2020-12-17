package com.meti.compile.feature.primitive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveTest {

	@Test
	void render() {
		assertEquals("int x", Primitive.I16.render("x"));
	}
}