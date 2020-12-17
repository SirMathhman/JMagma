package com.meti.compile;

import org.junit.jupiter.api.Test;

import static com.meti.compile.TokenizationStage.TokenizationStage_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenizationStageTest {

	@Test
	void apply() throws TokenizationException {
		assertEquals("int x=10;", TokenizationStage_.apply("const x : I16 = 10").render());
	}
}