package com.meti.compile.feature.scope;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.primitive.Primitive;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.field.FieldBuilders.FieldBuilder;
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
		var field = FieldBuilder()
				.withName("x")
				.withType(Primitive.I16)
				.complete();
		var node = new Declaration(field);
		return node;
	}
}