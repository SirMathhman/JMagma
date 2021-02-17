package com.meti.compile.feature.integer;

import org.junit.jupiter.api.Test;

import static com.meti.MagmaAssertions.assertCompile;

class IntegerFeatureTest {
	@Test
	void positive() {
		assertCompile("420", "420");
	}
}
