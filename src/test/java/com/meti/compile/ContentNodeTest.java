package com.meti.compile;

import org.junit.jupiter.api.Test;

import static com.meti.compile.ContentNode.ContentNode;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentNodeTest {
	@Test
	void testEquals() {
		assertEquals(ContentNode("test"), ContentNode("test"));
	}

	@Test
	void render() {
		assertEquals("test", ContentNode("test").render());
	}
}