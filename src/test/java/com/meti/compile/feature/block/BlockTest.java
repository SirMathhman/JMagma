package com.meti.compile.feature.block;

import com.meti.compile.feature.scope.Declaration;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.block.Block.Block;
import static com.meti.compile.feature.field.FieldBuilder.FieldBuilder;
import static com.meti.compile.feature.primitive.Primitive.U64;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockTest {
	@Test
	void testEquals() {
		var identity = FieldBuilder()
				.withName("x")
				.withType(U64)
				.complete();
		var declaration = new Declaration(identity);
		var node = Block(declaration, declaration);
		var node2 = Block(declaration, declaration);
		assertEquals(node, node2);
	}

	@Test
	void render() {
		var identity = FieldBuilder()
				.withName("x")
				.withType(U64)
				.complete();
		var node = Block(new Declaration(identity));
		assertEquals("{unsigned long long x;}", node.render());
	}
}