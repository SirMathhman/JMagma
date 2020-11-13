package com.meti.compile.generics;

import com.meti.compile.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericTypeTest {
	private static final GenericType Type_ = new GenericType("test");

	@Test
	void testEquals() {
		assertEquals(Type_, Type_);
	}

	@Test
	void render() {
		assertThrows(IllegalStateException.class, Type_::render);
	}

	@Test
	void is() {
		assertTrue(Type_.is(Type.Group.Generic));
	}
}