package com.meti.compile.feature.function;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.compile.feature.block.Block.Block;
import static com.meti.compile.feature.field.FieldBuilder.FieldBuilder;
import static com.meti.compile.feature.function.FunctionType.FunctionType;
import static com.meti.compile.feature.function.Implementation.Implementation;
import static com.meti.compile.feature.primitive.Primitive.U64;
import static com.meti.compile.feature.scope.Variable.Variable;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImplementationTest {
	@Test
	void testEquals() {
		assertEquals(createNode(), createNode());
	}

	@Test
	void render() {
		var value = createNode();
		assertEquals("unsigned long long pass(unsigned long long value){return value;}", value.render());
	}

	private Function createNode() {
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
		return Implementation(identity, List.of(parameter), Block(new Return(Variable("value"))));
	}
}