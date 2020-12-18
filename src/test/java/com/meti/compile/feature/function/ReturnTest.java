package com.meti.compile.feature.function;

import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.function.Return.Return;
import static com.meti.compile.feature.primitive.Int.Int;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReturnTest {
	@Test
	void testEquals(){
		assertEquals(Return(Int(10)), Return(Int(10)));
	}

	@Test
	void render() {
		var node = Return(Int(10));
		assertEquals("return 10;", node.render());
	}
}