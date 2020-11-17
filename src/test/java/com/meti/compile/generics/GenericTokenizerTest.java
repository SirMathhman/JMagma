package com.meti.compile.generics;

import com.meti.compile.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericTokenizerTest {
	@Test
	void simple() {
		Type expected = new GenericType("T");
		Type actual = new GenericTokenizer("T")
				.tokenize()
				.orElseThrow();
		assertEquals(expected, actual);
	}
}