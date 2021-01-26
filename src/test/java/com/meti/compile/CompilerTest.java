package com.meti.compile;

import org.junit.jupiter.api.Test;

import static com.meti.compile.Compiler.compile;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompilerTest {
	@Test
	void assign() {
		assertEquals("x=y;", compile("x=y;"));
	}

	@Test
	void assignment() {
		assertEquals("x=4;", compile("x=4;"));
	}

	@Test
	void block() {
		assertEquals("{}", compile("{}"));
	}

	@Test
	void blockWithChild() {
		assertEquals("{int x=0;}", compile("{const x : I16 = 0;}"));
	}

	@Test
	void blockWithChildren() {
		assertEquals("{int x=0;int y=420;}", compile("""
				{
					const x : I16 = 0;
					const y : I16 = 420;
				}
				"""));
	}

	@Test
	void construction() {
		assertEquals("{3,4}", compile("[Point]{3, 4}"));
	}

	@Test
	void declare() {
		assertEquals("int x=10;", compile("const x : I16 = 10;"));
	}

	@Test
	void dereference() {
		assertEquals("*value", compile("*value"));
	}

	@Test
	void doubling() {
		assertEquals("10.0d", compile("10.0d"));
	}

	@Test
	void elif() {
		assertEquals("if(1){}else if(1){}else{}", compile("""
				if(true){
				} elif(true){
				} else {
				}
				"""));
	}

	@Test
	void else_if() {
		assertEquals("if(1){}else{}", compile("if(true){}else{}"));
	}

	@Test
	void else_test() {
		assertEquals("else{}", compile("else {}"));
	}

	@Test
	void floating() {
		assertEquals("10.0", compile("10.0"));
	}

	@Test
	void function() {
		assertEquals("int main(){return 0;}", compile("def main() : I16 => {return 0;}"));
	}

	@Test
	void invoke() {
		assertEquals("main(test)", compile("main(test)"));
	}

	@Test
	void member() {
		assertEquals("myPoint.x", compile("myPoint => x"));
	}

	@Test
	void negativeInt() {
		assertEquals("-10", compile("-10"));
	}

	@Test
	void positiveInt() {
		assertEquals("10", compile("10"));
	}

	@Test
	void quantity() {
		assertEquals("(4)", compile("(4)"));
	}

	@Test
	void reference() {
		assertEquals("&value", compile("&value"));
	}

	@Test
	void returns() {
		assertEquals("return 420;", compile("return 420;"));
	}

	@Test
	void structure() {
		assertEquals("struct Wrapper{int value;};", compile("struct Wrapper{const value : I16}"));
	}

	@Test
	void test_false() {
		assertEquals("0", compile("false"));
	}

	@Test
	void test_true() {
		assertEquals("1", compile("true"));
	}

	@Test
	void variable() {
		assertEquals("test", compile("test"));
	}

	@Test
	void while_test() {
		assertEquals("while(1){}", compile("while(true){}"));
	}

	@Test
	void zero() {
		assertEquals("0", compile("0"));
	}
}