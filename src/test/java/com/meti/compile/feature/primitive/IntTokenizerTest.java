package com.meti.compile.feature.primitive;

import com.meti.compile.TokenizationException;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static com.meti.compile.feature.primitive.Int.Int;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntTokenizerTest {
	@Test
	void tokenize() throws TokenizationException {
		assertEquals("5", IntTokenizer.IntTokenizer_
				.tokenize("5")
				.orElse(Int(BigInteger.ZERO))
				.render());
	}
}