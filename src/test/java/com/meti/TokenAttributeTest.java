package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class TokenAttributeTest {

	@Test
	void computeToken() throws AttributeException {
		var expected = new Integer(new Input(""));
		var attribute = new TokenAttribute(expected);
		var actual = attribute.computeToken();
		assertSame(expected, actual);
	}
}