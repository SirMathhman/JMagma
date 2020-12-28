package com.meti.compile.feature.field;

import com.meti.compile.feature.primitive.Int;
import com.meti.compile.feature.primitive.Primitive;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.meti.compile.feature.field.Field.Flag.CONST;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ValueFieldTest {
	@Test
	void testEquals() {
		assertEquals(createField(), createField());
	}

	private Field createField() {
		return new ValueField(Set.of(CONST), "x", (Type) Primitive.I16, (Node) Int.Int(10));
	}

	@Test
	void render() {
		assertEquals("int x=10", createField().render());
	}
}