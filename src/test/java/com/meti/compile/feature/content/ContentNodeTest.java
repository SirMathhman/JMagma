package com.meti.compile.feature.content;

import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.content.ContentNode.ContentNode;
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