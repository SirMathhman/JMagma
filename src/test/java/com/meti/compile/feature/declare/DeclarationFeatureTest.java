package com.meti.compile.feature.declare;

import org.junit.jupiter.api.Test;

import static com.meti.MagmaAssertions.assertCompile;
import static com.meti.MagmaAssertions.assertCompileThrows;

class DeclarationFeatureTest {
	@Test
	void already_exists() {
		assertCompileThrows("x=420;x=10;");
	}

	@Test
	void implicit() {
		assertCompile("signed int x=420;", "x=420");
	}

	@Test
	void positive() {
		assertCompile("signed int x=420;", "x : I16 = 420");
	}
}
