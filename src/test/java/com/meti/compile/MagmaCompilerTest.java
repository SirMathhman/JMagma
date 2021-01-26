package com.meti.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagmaCompilerTest {
	@Test
	void assign() {
		assertEquals("x=y;", MagmaCompiler.MagmaCompiler_.compileLines("x=y;"));
	}

	@Test
	void assignment() {
		assertEquals("x=4;", MagmaCompiler.MagmaCompiler_.compileLines("x=4;"));
	}

	@Test
	void block() {
		assertEquals("{}", MagmaCompiler.MagmaCompiler_.compileLines("{}"));
	}

	@Test
	void blockWithChild() {
		assertEquals("{int x=0;}", MagmaCompiler.MagmaCompiler_.compileLines("{const x : I16 = 0;}"));
	}

	@Test
	void blockWithChildren() {
		assertEquals("{int x=0;int y=420;}", MagmaCompiler.MagmaCompiler_.compileLines("""
				{
					const x : I16 = 0;
					const y : I16 = 420;
				}
				"""));
	}

	@Test
	void construction() {
		assertEquals("{3,4}", MagmaCompiler.MagmaCompiler_.compileLines("[Point]{3, 4}"));
	}

	@Test
	void declare() {
		assertEquals("int x=10;", MagmaCompiler.MagmaCompiler_.compileLines("const x : I16 = 10;"));
	}

	@Test
	void dereference() {
		assertEquals("*value", MagmaCompiler.MagmaCompiler_.compileLines("*value"));
	}

	@Test
	void doubling() {
		assertEquals("10.0d", MagmaCompiler.MagmaCompiler_.compileLines("10.0d"));
	}

	@Test
	void elif() {
		assertEquals("if(1){}else if(1){}else{}", MagmaCompiler.MagmaCompiler_.compileLines("""
				if(true){
				} elif(true){
				} else {
				}
				"""));
	}

	@Test
	void else_if() {
		assertEquals("if(1){}else{}", MagmaCompiler.MagmaCompiler_.compileLines("if(true){}else{}"));
	}

	@Test
	void else_test() {
		assertEquals("else{}", MagmaCompiler.MagmaCompiler_.compileLines("else {}"));
	}

	@Test
	void floating() {
		assertEquals("10.0", MagmaCompiler.MagmaCompiler_.compileLines("10.0"));
	}

	@Test
	void function() {
		assertEquals("int main(){return 0;}", MagmaCompiler.MagmaCompiler_.compileLines("def main() : I16 => {return 0;}"));
	}

	@Test
	void invoke() {
		assertEquals("main(test)", MagmaCompiler.MagmaCompiler_.compileLines("main(test)"));
	}

	@Test
	void member() {
		assertEquals("myPoint.x", MagmaCompiler.MagmaCompiler_.compileLines("myPoint => x"));
	}

	@Test
	void negativeInt() {
		assertEquals("-10", MagmaCompiler.MagmaCompiler_.compileLines("-10"));
	}

	@Test
	void positiveInt() {
		assertEquals("10", MagmaCompiler.MagmaCompiler_.compileLines("10"));
	}

	@Test
	void quantity() {
		assertEquals("(4)", MagmaCompiler.MagmaCompiler_.compileLines("(4)"));
	}

	@Test
	void reference() {
		assertEquals("&value", MagmaCompiler.MagmaCompiler_.compileLines("&value"));
	}

	@Test
	void returns() {
		assertEquals("return 420;", MagmaCompiler.MagmaCompiler_.compileLines("return 420;"));
	}

	@Test
	void structure() {
		assertEquals("struct Wrapper{int value;};", MagmaCompiler.MagmaCompiler_.compileLines("struct Wrapper{const value : I16}"));
	}

	@Test
	void test_false() {
		assertEquals("0", MagmaCompiler.MagmaCompiler_.compileLines("false"));
	}

	@Test
	void test_true() {
		assertEquals("1", MagmaCompiler.MagmaCompiler_.compileLines("true"));
	}

	@Test
	void variable() {
		assertEquals("test", MagmaCompiler.MagmaCompiler_.compileLines("test"));
	}

	@Test
	void while_test() {
		assertEquals("while(1){}", MagmaCompiler.MagmaCompiler_.compileLines("while(true){}"));
	}

	@Test
	void zero() {
		assertEquals("0", MagmaCompiler.MagmaCompiler_.compileLines("0"));
	}
}