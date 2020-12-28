package com.meti.compile;

import com.meti.compile.token.TokenizationException;
import org.junit.jupiter.api.Test;

import static com.meti.compile.stage.MagmaTokenizationStage.MagmaTokenizationStage_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenizationStageTest {

	@Test
	void apply() throws TokenizationException {
		assertEquals("int x=10;", MagmaTokenizationStage_.tokenizeSingle("const x : I16 = 10").render());
	}
}