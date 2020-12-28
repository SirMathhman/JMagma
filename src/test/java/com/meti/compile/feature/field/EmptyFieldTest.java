package com.meti.compile.feature.field;

import com.meti.compile.feature.primitive.Primitive;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmptyFieldTest {

	@Test
	void render() {
		assertEquals("int x", createField().render());
	}

	private Field createField() {
		return new EmptyField(Set.of(Field.Flag.CONST), "x", Primitive.I16);
	}

	@Test
	void testEquals() {
		assertEquals(createField(), createField());
	}
}