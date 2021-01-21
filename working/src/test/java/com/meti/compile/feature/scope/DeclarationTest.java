package com.meti.compile.feature.scope;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.primitive.Primitive;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.field.FieldBuilder.FieldBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeclarationTest {

	@Test
	void render() {
		assertEquals("int x;", createDeclaration().render());
	}

	@Test
	void testEquals() {
		assertEquals(createDeclaration(), createDeclaration());
	}

	private Node createDeclaration() {
		return Declaration.Declaration(FieldBuilder()
				.withName("x")
				.withType(Primitive.I16)
				.complete());
	}
}