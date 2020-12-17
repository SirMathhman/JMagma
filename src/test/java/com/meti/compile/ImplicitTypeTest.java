package com.meti.compile;

import org.junit.jupiter.api.Test;

import static com.meti.compile.ImplicitType.ImplicitType_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImplicitTypeTest {

	@Test
	void render() {
		assertEquals("? x", ImplicitType_.render("x"));
	}
}