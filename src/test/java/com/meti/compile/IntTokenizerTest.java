package com.meti.compile;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static com.meti.compile.Int.Int;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntTokenizerTest {
	@Test
	void tokenize() {
		assertEquals("5", IntTokenizer.IntTokenizer_
				.tokenize("5")
				.orElse(Int(BigInteger.ZERO))
				.render());
	}
}