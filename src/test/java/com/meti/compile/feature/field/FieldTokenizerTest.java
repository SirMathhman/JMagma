package com.meti.compile.feature.field;

import com.meti.compile.token.TokenizationException;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.content.ContentNode.ContentNode;
import static com.meti.compile.feature.content.ContentType.ContentType;
import static com.meti.compile.feature.field.FieldTokenizer.FieldTokenizer_;
import static com.meti.compile.feature.ImplicitType.ImplicitType_;
import static com.meti.compile.feature.field.Field.Flag.CONST;
import static com.meti.compile.feature.field.FieldBuilder.FieldBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldTokenizerTest {

	@Test
	void tokenize() throws TokenizationException {
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
	void tokenizeWithoutType() throws TokenizationException {
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
	void tokenizeWithoutValue() throws TokenizationException {
		var expected = FieldBuilder()
				.withFlag(CONST)
				.withName("x")
				.withType(ContentType("I16"))
				.complete();
		var actual = FieldTokenizer_.tokenize("const x : I16").orElseThrow();
		assertEquals(expected, actual);
	}

	@Test
	void tokenizeInvalid() throws TokenizationException {
		assertTrue(FieldTokenizer_.tokenize("5").isEmpty());
	}
}