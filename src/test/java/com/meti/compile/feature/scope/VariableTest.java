package com.meti.compile.feature.scope;

import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.scope.Variable.Variable;
import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

	@Test
	void testEquals() {
		assertEquals(Variable("test"), Variable("test"));
	}

	@Test
	void render() {
		assertEquals("test", Variable("test").render());
	}
}