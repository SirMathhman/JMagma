package com.meti.compile.feature.field;

import com.meti.compile.feature.primitive.Int;
import com.meti.compile.feature.primitive.Primitive;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.meti.compile.feature.field.Field.Flag.CONST;
import static com.meti.compile.feature.field.ValueField.ValueField;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ValueFieldTest {
	@Test
	void testEquals() {
		assertEquals(createField(), createField());
	}

	private Field createField() {
		return ValueField(Set.of(CONST), "x", Primitive.I16, Int.Int(10));
	}

	@Test
	void render() {
		assertEquals("int x=10", createField().render());
	}
}