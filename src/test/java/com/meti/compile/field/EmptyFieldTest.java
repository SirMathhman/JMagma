package com.meti.compile.field;

import com.meti.compile.Primitive;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.meti.compile.field.EmptyField.EmptyField;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmptyFieldTest {

	@Test
	void render() {
		assertEquals("int x", createField().render());
	}

	private Field createField() {
		return EmptyField(Set.of(Field.Flag.CONST), "x", Primitive.I16);
	}

	@Test
	void testEquals() {
		assertEquals(createField(), createField());
	}
}