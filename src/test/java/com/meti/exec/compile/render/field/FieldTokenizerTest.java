package com.meti.exec.compile.render.field;

import com.meti.exec.compile.render.TokenizationException;
import org.junit.jupiter.api.Test;

import static com.meti.exec.compile.render.field.FieldTokenizer.FieldTokenizer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FieldTokenizerTest {
	@Test
	void tokenizeBoth() throws TokenizationException {
		assertEquals("I16 x=10", FieldTokenizer("const x : I16 = 10")
				.tokenize()
				.flatMap(Field::render)
				.orElse(""));
	}

	@Test
	void tokenizeType() throws TokenizationException {
		assertEquals("I16 x", FieldTokenizer("const x : I16")
				.tokenize()
				.flatMap(Field::render)
				.orElse(""));
	}

	@Test
	void tokenizeDefaultValue() throws TokenizationException {
		assertEquals("? x=10", FieldTokenizer("const x = 10")
				.tokenize()
				.flatMap(Field::render)
				.orElse(""));
	}

	@Test
	void tokenizeInvalid() {
		assertThrows(TokenizationException.class, () -> FieldTokenizer("const x").tokenize());
	}
}