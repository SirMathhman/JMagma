package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class InputAttributeTest {

	@Test
	void computeInput() throws AttributeException {
		var expected = new Input("test");
		var attribute = new InputAttribute(expected);
		var actual = attribute.computeInput();
		assertSame(expected, actual);
	}
}