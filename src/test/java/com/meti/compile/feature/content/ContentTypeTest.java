package com.meti.compile.feature.content;

import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.content.ContentType.ContentType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentTypeTest {
	@Test
	void testEquals(){
		assertEquals(ContentType("test"), ContentType("test"));
	}

	@Test
	void render() {
		assertEquals("int x", ContentType("int").render("x"));
	}
}