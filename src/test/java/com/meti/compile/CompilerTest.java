package com.meti.compile;

import org.junit.jupiter.api.Test;

import static com.meti.compile.Compiler.compileLines;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompilerTest {
	@Test
	void assign() {
		assertEquals("x=y;", compileLines("x=y;"));
	}

	@Test
	void assignment() {
		assertEquals("x=4;", compileLines("x=4;"));
	}

	@Test
	void block() {
		assertEquals("{}", compileLines("{}"));
	}

	@Test
	void blockWithChild() {
		assertEquals("{int x=0;}", compileLines("{const x : I16 = 0;}"));
	}

	@Test
	void blockWithChildren() {
		assertEquals("{int x=0;int y=420;}", compileLines("""
				{
					const x : I16 = 0;
					const y : I16 = 420;
				}
				"""));
	}

	@Test
	void construction() {
		assertEquals("{3,4}", compileLines("[Point]{3, 4}"));
	}

	@Test
	void declare() {
		assertEquals("int x=10;", compileLines("const x : I16 = 10;"));
	}

	@Test
	void dereference() {
		assertEquals("*value", compileLines("*value"));
	}

	@Test
	void doubling() {
		assertEquals("10.0d", compileLines("10.0d"));
	}

	@Test
	void elif() {
		assertEquals("if(1){}else if(1){}else{}", compileLines("""
				if(true){
				} elif(true){
				} else {
				}
				"""));
	}

	@Test
	void else_if() {
		assertEquals("if(1){}else{}", compileLines("if(true){}else{}"));
	}

	@Test
	void else_test() {
		assertEquals("else{}", compileLines("else {}"));
	}

	@Test
	void floating() {
		assertEquals("10.0", compileLines("10.0"));
	}

	@Test
	void function() {
		assertEquals("int main(){return 0;}", compileLines("def main() : I16 => {return 0;}"));
	}

	@Test
	void invoke() {
		assertEquals("main(test)", compileLines("main(test)"));
	}

	@Test
	void member() {
		assertEquals("myPoint.x", compileLines("myPoint => x"));
	}

	@Test
	void negativeInt() {
		assertEquals("-10", compileLines("-10"));
	}

	@Test
	void positiveInt() {
		assertEquals("10", compileLines("10"));
	}

	@Test
	void quantity() {
		assertEquals("(4)", compileLines("(4)"));
	}

	@Test
	void reference() {
		assertEquals("&value", compileLines("&value"));
	}

	@Test
	void returns() {
		assertEquals("return 420;", compileLines("return 420;"));
	}

	@Test
	void structure() {
		assertEquals("struct Wrapper{int value;};", compileLines("struct Wrapper{const value : I16}"));
	}

	@Test
	void test_false() {
		assertEquals("0", compileLines("false"));
	}

	@Test
	void test_true() {
		assertEquals("1", compileLines("true"));
	}

	@Test
	void variable() {
		assertEquals("test", compileLines("test"));
	}

	@Test
	void while_test() {
		assertEquals("while(1){}", compileLines("while(true){}"));
	}

	@Test
	void zero() {
		assertEquals("0", compileLines("0"));
	}
}