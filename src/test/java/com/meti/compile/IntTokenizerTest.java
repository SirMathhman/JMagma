package com.meti.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntTokenizerTest {

	@Test
	void tokenize() {
		assertEquals("5", IntTokenizer.IntTokenizer_
				.tokenizeToString("5")
				.orElse(""));
	}
}