package com.meti.compile.feature.condition;

import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.condition.Boolean.Boolean;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BooleanTest {

	@Test
	void render() {
		assertEquals("true", Boolean(true).render());
	}

	@Test
	void testEquals() {
		assertEquals(Boolean(true), Boolean(true));
	}
}