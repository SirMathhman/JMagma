package com.meti.compile.feature.condition;

import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.block.Block.Block;
import static com.meti.compile.feature.condition.Boolean.False;
import static com.meti.compile.feature.condition.Boolean.True;
import static com.meti.compile.feature.condition.Conditional.If;
import static com.meti.compile.feature.condition.Conditional.While;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConditionalTest {

	@Test
	void testEquals() {
		var conditional = If(False, Block());
		assertEquals(conditional, conditional);
	}

	@Test
	void render() {
		var conditional = If(False, Block());
		assertEquals("if(0){}", conditional.render());
	}

	@Test
	void renderWhile() {
		var conditional = While(True, Block());
		assertEquals("while(1){}", conditional.render());
	}
}