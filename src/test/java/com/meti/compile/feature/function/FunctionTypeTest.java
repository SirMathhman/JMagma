package com.meti.compile.feature.function;

import com.meti.compile.feature.Type;
import com.meti.compile.feature.primitive.Primitive;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.function.FunctionType.FunctionType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTypeTest {
	@Test
	void findChild() {
		assertEquals(Primitive.I8, createType().findChild().orElseThrow());
	}

	@Test
	void render() {
		var type = createType();
		assertEquals("char (*test)(unsigned long)", type.render("test"));
	}

	private Type createType() {
		return FunctionType()
				.withParameter(Primitive.U32)
				.withReturnType(Primitive.I8)
				.complete();
	}
}