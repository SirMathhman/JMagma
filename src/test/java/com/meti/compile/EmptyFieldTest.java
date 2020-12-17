package com.meti.compile;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.meti.compile.EmptyField.EmptyField;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmptyFieldTest {

	@Test
	void render() {
		assertEquals("int x", EmptyField(Set.of(Field.Flag.CONST), "x", Primitive.I16).render());
	}
}