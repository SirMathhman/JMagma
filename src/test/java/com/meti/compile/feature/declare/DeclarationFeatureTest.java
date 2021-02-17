package com.meti.compile.feature.declare;

import org.junit.jupiter.api.Test;

import static com.meti.MagmaAssertions.assertCompile;
import static com.meti.MagmaAssertions.assertCompileThrows;

class DeclarationFeatureTest {
	@Test
	void already_exists() {
		assertCompileThrows("const x=420;const x=10;");
	}

	@Test
	void implicit() {
		assertCompile("const x=420", "signed int x=420;");
	}

	@Test
	void positive() {
		assertCompile("const x : I16 = 420", "signed int x=420;");
	}
}
