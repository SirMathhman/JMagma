package com.meti.compile.feature.function;

import com.meti.compile.token.Node;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.function.Invocation.Invocation;
import static com.meti.compile.feature.primitive.Int.Int;
import static com.meti.compile.feature.scope.Variable.Variable;
import static org.junit.jupiter.api.Assertions.*;

class InvocationTest {
	private Node createNode() {
		return Invocation()
				.withCaller(Variable("myFunction"))
				.withArgument(Int(10))
				.withArgument(Int(20))
				.complete();
	}

	@Test
	void testEquals() {
		assertEquals(createNode(), createNode());
	}

	@Test
	void render() {
		assertEquals("myFunction(10,20)", createNode().render());
	}
}