package com.meti.compile.feature.scope;

import com.meti.compile.CompiledTest;
import org.junit.jupiter.api.Test;

class DeclarationFeatureTest extends CompiledTest {
	@Test
	void compileDeclarations() {
		assertSource("const x : I16 = 10", "int x=10;", "");
	}


}
