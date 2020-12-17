package com.meti.compile;

import org.junit.jupiter.api.Test;

import static com.meti.compile.ContentNode.ContentNode;
import static com.meti.compile.ContentType.ContentType;
import static com.meti.compile.FieldTokenizer.FieldTokenizer_;
import static com.meti.compile.ImplicitType.ImplicitType_;
import static com.meti.compile.field.Field.Flag.CONST;
import static com.meti.compile.field.FieldBuilders.FieldBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldTokenizerTest {

	@Test
	void tokenize() {
		var expected = FieldBuilder()
				.withFlag(CONST)
				.withName("x")
				.withType(ContentType("I16"))
				.withValue(ContentNode("10"))
				.complete();
		var actual = FieldTokenizer_.tokenize("const x : I16 = 10").orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	void tokenizeWithoutType() {
		var expected = FieldBuilder()
				.withFlag(CONST)
				.withName("x")
				.withType(ImplicitType_)
				.withValue(ContentNode("10"))
				.complete();
		var actual = FieldTokenizer_.tokenize("const x = 10").orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	void tokenizeWithoutValue() {
		var expected = FieldBuilder()
				.withFlag(CONST)
				.withName("x")
				.withType(ContentType("I16"))
				.complete();
		var actual = FieldTokenizer_.tokenize("const x : I16").orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	void tokenizeInvalid() {
		assertTrue(FieldTokenizer_.tokenize("5").isEmpty());
	}
}