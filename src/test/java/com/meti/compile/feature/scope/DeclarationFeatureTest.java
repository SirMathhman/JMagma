package com.meti.compile.feature.scope;

import com.meti.compile.CompiledTest;
import org.junit.jupiter.api.Test;

class DeclarationFeatureTest extends CompiledTest {
	@Test
	void test() {
		assertSource("const x : I16 = 10", "int x=10;");
	}

	@Test
	void withoutValue(){
		assertSource("let x : U64", "unsigned long long x;");
	}
}
