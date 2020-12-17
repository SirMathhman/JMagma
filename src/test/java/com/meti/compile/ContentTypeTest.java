package com.meti.compile;

import org.junit.jupiter.api.Test;

import static com.meti.compile.ContentType.ContentType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentTypeTest {

	@Test
	void render() {
		assertEquals("int x", ContentType("int").render("x"));
	}
}