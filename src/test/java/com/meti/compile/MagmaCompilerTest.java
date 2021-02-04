package com.meti.compile;

import org.junit.jupiter.api.Test;

import static com.meti.compile.MagmaCompiler.MagmaCompiler_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MagmaCompilerTest {
	@Test
	void assign() {
		assertEquals("x=y;", MagmaCompiler_.compile("x=y;"));
	}

	@Test
	void assignment() {
		assertEquals("x=4;", MagmaCompiler_.compile("x=4;"));
	}

	@Test
	void block() {
		assertEquals("{}", MagmaCompiler_.compile("{}"));
	}

	@Test
	void blockWithChild() {
		assertEquals("{int x=0;}", MagmaCompiler_.compile("{const x : I16 = 0;}"));
	}

	@Test
	void blockWithChildren() {
		assertEquals("{int x=0;int y=420;}", MagmaCompiler_.compile("""
				{
					const x : I16 = 0;
					const y : I16 = 420;
				}
				"""));
	}

	@Test
	void construction() {
		assertEquals("{3,4}", MagmaCompiler_.compile("[Point]{3, 4}"));
	}

	@Test
	void declare() {
		assertEquals("int x=10;", MagmaCompiler_.compile("const x : I16 = 10;"));
	}

	@Test
	void dereference() {
		assertEquals("*value", MagmaCompiler_.compile("*value"));
	}

	@Test
	void doubling() {
		assertEquals("10.0d", MagmaCompiler_.compile("10.0d"));
	}

	@Test
	void elif() {
		assertEquals("if(1){}else if(1){}else{}", MagmaCompiler_.compile("""
				if(true){
				} elif(true){
				} else {
				}
				"""));
	}

	@Test
	void else_if() {
		assertEquals("if(1){}else{}", MagmaCompiler_.compile("if(true){}else{}"));
	}

	@Test
	void else_test() {
		assertEquals("else{}", MagmaCompiler_.compile("else {}"));
	}

	@Test
	void floating() {
		assertEquals("10.0", MagmaCompiler_.compile("10.0"));
	}

	@Test
	void function() {
		assertEquals("int main(){return 0;}", MagmaCompiler_.compile("def main() : I16 => {return 0;}"));
	}

	@Test
	void invoke() {
		assertEquals("main(test)", MagmaCompiler_.compile("main(test)"));
	}

	@Test
	void member() {
		assertEquals("myPoint.x", MagmaCompiler_.compile("myPoint => x"));
	}

	@Test
	void negativeInt() {
		assertEquals("-10", MagmaCompiler_.compile("-10"));
	}

	@Test
	void positiveInt() {
		assertEquals("10", MagmaCompiler_.compile("10"));
	}

	@Test
	void quantity() {
		assertEquals("(4)", MagmaCompiler_.compile("(4)"));
	}

	@Test
	void reference() {
		assertEquals("&value", MagmaCompiler_.compile("&value"));
	}

	@Test
	void returns() {
		assertEquals("return 420;", MagmaCompiler_.compile("return 420;"));
	}

	@Test
	void structure() {
		assertEquals("struct Wrapper{int value;};", MagmaCompiler_.compile("struct Wrapper{const value : I16}"));
	}

	@Test
	void test_false() {
		assertEquals("0", MagmaCompiler_.compile("false"));
	}

	@Test
	void test_true() {
		assertEquals("1", MagmaCompiler_.compile("true"));
	}

	@Test
	void variable() {
		assertEquals("test", MagmaCompiler_.compile("test"));
	}

	@Test
	void while_test() {
		assertEquals("while(1){}", MagmaCompiler_.compile("while(true){}"));
	}

	@Test
	void zero() {
		assertEquals("0", MagmaCompiler_.compile("0"));
	}
}