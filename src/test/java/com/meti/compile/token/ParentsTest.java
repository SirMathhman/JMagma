package com.meti.compile.token;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParentsTest {
	@Test
	void format() {
		var value = new Content("0");
		var actual = Parents.format("return %t;")
				.format(value)
				.complete()
				.apply(AbstractToken.Query.Value)
				.asString();
		assertEquals("return 0;", actual);
	}

	@Test
	void formatSubClass() {
		var value = new Content("0");
		var actual = new Parents.Formatter("return %t;", Collections.emptyList())
				.format(value)
				.complete()
				.apply(AbstractToken.Query.Value)
				.asString();
		assertEquals("return 0;", actual);
	}
}