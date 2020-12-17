package com.meti.compile.feature.scope;

import com.meti.compile.feature.primitive.Primitive;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.field.FieldBuilders.FieldBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeclarationTest {

	@Test
	void render() {
		var field = FieldBuilder()
				.withName("x")
				.withType(Primitive.I16)
				.complete();
		var node = new Declaration(field);
		assertEquals("int x;", node.render());
	}
}