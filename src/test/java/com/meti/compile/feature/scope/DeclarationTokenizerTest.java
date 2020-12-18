package com.meti.compile.feature.scope;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.field.Field;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.content.ContentNode.ContentNode;
import static com.meti.compile.feature.content.ContentType.ContentType;
import static com.meti.compile.feature.field.FieldBuilders.FieldBuilder;
import static com.meti.compile.feature.primitive.Int.Int;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeclarationTokenizerTest {
	private Node createDeclaration() {
		return Declaration.Declaration(FieldBuilder()
				.withFlag(Field.Flag.CONST)
				.withName("x")
				.withType(ContentType("I16"))
				.withValue(ContentNode("10"))
				.complete());
	}

	@Test
	void tokenize() {
		assertEquals(createDeclaration(), DeclarationTokenizer.DeclarationTokenizer_
				.tokenize("const x : I16 = 10")
				.orElseThrow());
	}
}