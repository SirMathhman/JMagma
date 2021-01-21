package com.meti.compile.feature.reference;

import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.primitive.Primitive.I64;
import static com.meti.compile.feature.reference.RefType.RefType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RefTypeTest {
	@Test
	void testEquals() {
		assertEquals(RefType(I64), RefType(I64));
	}

	@Test
	void render() {
		assertEquals("long long * value", RefType(I64).render("value"));
	}
}