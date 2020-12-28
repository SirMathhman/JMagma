package com.meti.compile.feature.scope;

import com.meti.compile.CompiledTest;
import com.meti.compile.feature.field.FlagException;
import org.junit.jupiter.api.Test;

class DeclarationFeatureTest extends CompiledTest {
	@Test
	void test() {
		assertSource("const x : I16 = 10", "int x=10;");
	}

	@Test
	void withoutValue() {
		assertSource("let x : U64", "unsigned long long x;");
	}

	@Test
	void bothFlags() {
		assertSourceThrows(FlagException.class, "let const x : U32");
	}

	@Test
	void implicitType(){
		assertSource("let x = 10", "int x=10;");
	}
}
