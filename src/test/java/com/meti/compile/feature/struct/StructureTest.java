package com.meti.compile.feature.struct;

import com.meti.compile.token.Node;
import com.meti.compile.feature.primitive.Primitive;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.field.FieldBuilder.FieldBuilder;
import static com.meti.compile.feature.struct.Structure.Structure;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StructureTest {
	private Node createStructure() {
		var field = FieldBuilder()
				.withName("value")
				.withType(Primitive.I32)
				.complete();
		return Structure()
				.withName("Wrapper")
				.withField(field)
				.complete();
	}

	@Test
	void testEquals() {
		assertEquals(createStructure(), createStructure());
	}

	@Test
	void render() {
		assertEquals("struct Wrapper{long value;}", createStructure().render());
	}
}