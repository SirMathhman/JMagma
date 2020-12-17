package com.meti.compile.feature;

import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.ImplicitType.ImplicitType_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImplicitTypeTest {

	@Test
	void render() {
		assertEquals("? x", ImplicitType_.render("x"));
	}
}