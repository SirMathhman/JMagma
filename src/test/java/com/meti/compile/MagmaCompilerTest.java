package com.meti.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagmaCompilerTest {
	@Test
	void assign() {
		assertEquals("x=y;", MagmaCompiler.MagmaCompiler_.compileAll("x=y;"));
	}

	@Test
	void assignment() {
		assertEquals("x=4;", MagmaCompiler.MagmaCompiler_.compileAll("x=4;"));
	}

	@Test
	void block() {
		assertEquals("{}", MagmaCompiler.MagmaCompiler_.compileAll("{}"));
	}

	@Test
	void blockWithChild() {
		assertEquals("{int x=0;}", MagmaCompiler.MagmaCompiler_.compileAll("{const x : I16 = 0;}"));
	}

	@Test
	void blockWithChildren() {
		assertEquals("{int x=0;int y=420;}", MagmaCompiler.MagmaCompiler_.compileAll("""
				{
					const x : I16 = 0;
					const y : I16 = 420;
				}
				"""));
	}

	@Test
	void construction() {
		assertEquals("{3,4}", MagmaCompiler.MagmaCompiler_.compileAll("[Point]{3, 4}"));
	}

	@Test
	void declare() {
		assertEquals("int x=10;", MagmaCompiler.MagmaCompiler_.compileAll("const x : I16 = 10;"));
	}

	@Test
	void dereference() {
		assertEquals("*value", MagmaCompiler.MagmaCompiler_.compileAll("*value"));
	}

	@Test
	void doubling() {
		assertEquals("10.0d", MagmaCompiler.MagmaCompiler_.compileAll("10.0d"));
	}

	@Test
	void elif() {
		assertEquals("if(1){}else if(1){}else{}", MagmaCompiler.MagmaCompiler_.compileAll("""
				if(true){
				} elif(true){
				} else {
				}
				"""));
	}

	@Test
	void else_if() {
		assertEquals("if(1){}else{}", MagmaCompiler.MagmaCompiler_.compileAll("if(true){}else{}"));
	}

	@Test
	void else_test() {
		assertEquals("else{}", MagmaCompiler.MagmaCompiler_.compileAll("else {}"));
	}

	@Test
	void floating() {
		assertEquals("10.0", MagmaCompiler.MagmaCompiler_.compileAll("10.0"));
	}

	@Test
	void function() {
		assertEquals("int main(){return 0;}", MagmaCompiler.MagmaCompiler_.compileAll("def main() : I16 => {return 0;}"));
	}

	@Test
	void invoke() {
		assertEquals("main(test)", MagmaCompiler.MagmaCompiler_.compileAll("main(test)"));
	}

	@Test
	void member() {
		assertEquals("myPoint.x", MagmaCompiler.MagmaCompiler_.compileAll("myPoint => x"));
	}

	@Test
	void negativeInt() {
		assertEquals("-10", MagmaCompiler.MagmaCompiler_.compileAll("-10"));
	}

	@Test
	void positiveInt() {
		assertEquals("10", MagmaCompiler.MagmaCompiler_.compileAll("10"));
	}

	@Test
	void quantity() {
		assertEquals("(4)", MagmaCompiler.MagmaCompiler_.compileAll("(4)"));
	}

	@Test
	void reference() {
		assertEquals("&value", MagmaCompiler.MagmaCompiler_.compileAll("&value"));
	}

	@Test
	void returns() {
		assertEquals("return 420;", MagmaCompiler.MagmaCompiler_.compileAll("return 420;"));
	}

	@Test
	void structure() {
		assertEquals("struct Wrapper{int value;};", MagmaCompiler.MagmaCompiler_.compileAll("struct Wrapper{const value : I16}"));
	}

	@Test
	void test_false() {
		assertEquals("0", MagmaCompiler.MagmaCompiler_.compileAll("false"));
	}

	@Test
	void test_true() {
		assertEquals("1", MagmaCompiler.MagmaCompiler_.compileAll("true"));
	}

	@Test
	void variable() {
		assertEquals("test", MagmaCompiler.MagmaCompiler_.compileAll("test"));
	}

	@Test
	void while_test() {
		assertEquals("while(1){}", MagmaCompiler.MagmaCompiler_.compileAll("while(true){}"));
	}

	@Test
	void zero() {
		assertEquals("0", MagmaCompiler.MagmaCompiler_.compileAll("0"));
	}
}