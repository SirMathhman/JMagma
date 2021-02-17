package com.meti.compile.feature.assign;

import org.junit.jupiter.api.Test;

import static com.meti.MagmaAssertions.assertCompile;
import static com.meti.MagmaAssertions.assertCompileThrows;

class AssignmentFeatureTest {
	@Test
	void constant() {
		assertCompileThrows("const x=420;x=5;");
	}

	@Test
	void defined() {
		assertCompile("let x=420;x=5;", "signed int x=420;x=5");
	}

	@Test
	void undefined() {
		assertCompileThrows("x = 5;");
	}
}
