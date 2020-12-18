package com.meti.compile.feature.function;

import com.meti.compile.feature.primitive.Primitive;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.function.FunctionType.FunctionType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTypeTest {

	@Test
	void render() {
		var type = FunctionType()
				.withParameter(Primitive.U32)
				.withReturnType(Primitive.Void)
				.complete();
		assertEquals("void (*test)(unsigned long)", type.render("test"));
	}
}