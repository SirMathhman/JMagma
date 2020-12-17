package com.meti.compile;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IntTokenizerTest {

	@Test
	void tokenize() {
		assertEquals("5", IntTokenizer.IntTokenizer_
				.tokenize("5")
				.orElse(""));
	}
}