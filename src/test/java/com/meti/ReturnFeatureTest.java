package com.meti;

import org.junit.jupiter.api.Test;

import static com.meti.MagmaAssertions.assertCompile;

class ReturnFeatureTest {
	@Test
	void test() throws CompileException {
		assertCompile("return 420", "return 420");
	}
}
