package com.meti.exec.compile.render.field;

import com.meti.exec.compile.render.TokenizationException;
import org.junit.jupiter.api.Test;

import static com.meti.exec.compile.render.ContentType.ContentType;
import static com.meti.exec.compile.render.field.FieldTokenizer.FieldTokenizer;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FieldTokenizerTest {
	@Test
	void tokenizeInvalid() {
		assertThrows(TokenizationException.class, () -> FieldTokenizer("const x").tokenize());
	}
}