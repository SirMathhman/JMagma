package com.meti.compile.feature.primitive;

import com.meti.compile.token.TokenizationException;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.primitive.PrimitiveTokenizer.PrimitiveTokenizer_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimitiveTokenizerTest {

	@Test
	void tokenize() throws TokenizationException {
		assertEquals(Primitive.I16, PrimitiveTokenizer_.tokenize("I16").orElseThrow());
	}
}