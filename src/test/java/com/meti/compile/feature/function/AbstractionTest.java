package com.meti.compile.feature.function;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.compile.feature.field.FieldBuilders.FieldBuilder;
import static com.meti.compile.feature.function.Abstraction.Abstraction;
import static com.meti.compile.feature.function.FunctionType.FunctionType;
import static com.meti.compile.feature.primitive.Primitive.U64;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractionTest {
	@Test
	void testEquals() {
		assertEquals(createNode(), createNode());
	}

	@Test
	void render() {
		var value = createNode();
		assertEquals("unsigned long long pass(unsigned long long value);", value.render());
	}

	private Abstraction createNode() {
		var identity = FieldBuilder()
				.withName("pass")
				.withType(FunctionType()
						.withReturnType(U64)
						.withParameter(U64)
						.complete()
				).complete();
		var parameter = FieldBuilder()
				.withName("value")
				.withType(U64)
				.complete();
		return Abstraction(identity, List.of(parameter));
	}
}