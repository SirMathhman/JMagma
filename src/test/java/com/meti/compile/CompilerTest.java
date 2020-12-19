package com.meti.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CompilerTest {
	private static final Compiler Compiler = new Compiler();

	@Test
	void nativeImports(){
		assertCompile("#include <stdio.h>\n", "import native stdio");
	}

	@Test
	void invocations() {
		assertCompile("myFunction(10,20)", "myFunction(10, 20)");
	}

	@Test
	void structure() {
		assertCompile("struct Wrapper{int value;}", "struct Wrapper {const value : I16}");
	}

	@Test
	void emptyStructure() {
		assertCompile("struct Empty{}", "struct Empty{}");
	}

	private void assertCompile(String s, String s2) {
		try {
			assertEquals(s, Compiler.compile(s2));
		} catch (CompileException e) {
			fail(e);
		}
	}

	@Test
	void testTrue() {
		assertCompile("1", "true");
	}

	@Test
	void testFalse() {
		assertCompile("0", "false");
	}

	@Test
	void testIf() {
		assertCompile("if(1){}", "if(true){}");
	}

	@Test
	void testWhile() {
		assertCompile("while(0){}", "while(false){}");
	}

	@Test
	void compileDeclarations() {
		assertCompile("int x=10;", "const x : I16 = 10");
	}

	@Test
	void compileBlocks() {
		assertCompile("{{}{}}", "{{}{}}");
	}

	@Test
	void blockChildren() {
		assertCompile("{return 0;}", "{return 0;}");
	}

	@Test
	void compileReturn() {
		assertCompile("return 10;", "return 10");
	}

	@Test
	void compileMain() {
		assertCompile("int main(){return 0;}", "def main() : I16 => {return 0;}");
	}

	@Test
	void compileInt() {
		assertCompile("5", "5");
	}
}