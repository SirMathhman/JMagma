package com.meti.compile.feature.condition;

import com.meti.compile.TokenizationException;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.condition.Boolean.Boolean;
import static com.meti.compile.feature.condition.BooleanTokenizer.BooleanTokenizer_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BooleanTokenizerTest {
	@Test
	void testTrue() throws TokenizationException {
		assertEquals(Boolean(true), BooleanTokenizer_.tokenize("true").orElseThrow());
	}

	@Test
	void testFalse() throws TokenizationException {
		assertEquals(Boolean(false), BooleanTokenizer_.tokenize("false").orElseThrow());
	}
}