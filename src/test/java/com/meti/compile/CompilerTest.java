package com.meti.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompilerTest {
	private static final Compiler Compiler = new Compiler();

	@Test
	void testTrue() throws CompileException {
		assertEquals("1", Compiler.compile("true"));
	}

	@Test
	void testFalse() throws CompileException {
		assertEquals("0", Compiler.compile("false"));
	}

	@Test
	void testIf() throws CompileException {
		assertEquals("if(1){}", Compiler.compile("if(true){}"));
	}

	@Test
	void testWhile() throws CompileException {
		assertEquals("while(0){}", Compiler.compile("while(false){}"));
	}

	@Test
	void compileDeclarations() throws CompileException {
		assertEquals("int x=10;", Compiler.compile("const x : I16 = 10"));
	}

	@Test
	void compileBlocks() throws CompileException {
		assertEquals("{{}{}}", Compiler.compile("{{}{}}"));
	}

	@Test
	void blockChildren() throws CompileException {
		assertEquals("{return 0;}", Compiler.compile("{return 0;}"));
	}

	@Test
	void compileReturn() throws CompileException {
		assertEquals("return 10;", Compiler.compile("return 10"));
	}

	@Test
	void compileMain() throws CompileException {
		assertEquals("int main(){return 0;}", Compiler.compile("def main() : I16 => {return 0;}"));
	}

	@Test
	void compileInt() throws CompileException {
		assertEquals("5", Compiler.compile("5"));
	}
}