package com.meti.compile.feature.function;

import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.primitive.Int.Int;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReturnTest {
	@Test
	void testEquals(){
		assertEquals(new Return(Int(10)), new Return(Int(10)));
	}

	@Test
	void render() {
		var node = new Return(Int(10));
		assertEquals("return 10;", node.render());
	}
}