package com.meti.compile;

import com.meti.compile.token.Input;
import org.junit.jupiter.api.Test;

import static com.meti.compile.MagmaCompiler.MagmaCompiler_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MagmaCompilerTest {
	@Test
	void assign() {
		assertEquals("x=y;", MagmaCompiler_.compile(new Input("x=y;")).getValue());
	}

	@Test
	void assignment() {
		assertEquals("x=4;", MagmaCompiler_.compile(new Input("x=4;")).getValue());
	}

	@Test
	void block() {
		assertEquals("{}", MagmaCompiler_.compile(new Input("{}")).getValue());
	}

	@Test
	void blockWithChild() {
		assertEquals("{int x=0;}", MagmaCompiler_.compile(new Input("{const x : I16 = 0;}")).getValue());
	}

	@Test
	void blockWithChildren() {
		assertEquals("{int x=0;int y=420;}", MagmaCompiler_.compile(new Input("""
				{
					const x : I16 = 0;
					const y : I16 = 420;
				}
				""")).getValue());
	}

	@Test
	void construction() {
		assertEquals("{3,4}", MagmaCompiler_.compile(new Input("[Point]{3, 4}")).getValue());
	}

	@Test
	void declare() {
		assertEquals("int x=10;", MagmaCompiler_.compile(new Input("const x : I16 = 10;")).getValue());
	}

	@Test
	void dereference() {
		assertEquals("*value", MagmaCompiler_.compile(new Input("*value")).getValue());
	}

	@Test
	void doubling() {
		assertEquals("10.0d", MagmaCompiler_.compile(new Input("10.0d")).getValue());
	}

	@Test
	void elif() {
		assertEquals("if(1){}else if(1){}else{}", MagmaCompiler_.compile(new Input("""
				if(true){
				} elif(true){
				} else {
				}
				""")).getValue());
	}

	@Test
	void else_if() {
		assertEquals("if(1){}else{}", MagmaCompiler_.compile(new Input("if(true){}else{}")).getValue());
	}

	@Test
	void else_test() {
		assertEquals("else{}", MagmaCompiler_.compile(new Input("else {}")).getValue());
	}

	@Test
	void floating() {
		assertEquals("10.0", MagmaCompiler_.compile(new Input("10.0")).getValue());
	}

	@Test
	void function() {
		assertEquals("int main(){return 0;}", MagmaCompiler_.compile(new Input("def main() : I16 => {return 0;}")).getValue());
	}

	@Test
	void invoke() {
		assertEquals("main(test)", MagmaCompiler_.compile(new Input("main(test)")).getValue());
	}

	@Test
	void member() {
		assertEquals("myPoint.x", MagmaCompiler_.compile(new Input("myPoint => x")).getValue());
	}

	@Test
	void negativeInt() {
		assertEquals("-10", MagmaCompiler_.compile(new Input("-10")).getValue());
	}

	@Test
	void positiveInt() {
		assertEquals("10", MagmaCompiler_.compile(new Input("10")).getValue());
	}

	@Test
	void quantity() {
		assertEquals("(4)", MagmaCompiler_.compile(new Input("(4)")).getValue());
	}

	@Test
	void reference() {
		assertEquals("&value", MagmaCompiler_.compile(new Input("&value")).getValue());
	}

	@Test
	void returns() {
		assertEquals("return 420;", MagmaCompiler_.compile(new Input("return 420;")).getValue());
	}

	@Test
	void structure() {
		assertEquals("struct Wrapper{int value;};", MagmaCompiler_.compile(new Input("struct Wrapper{const value : I16}")).getValue());
	}

	@Test
	void test_false() {
		assertEquals("0", MagmaCompiler_.compile(new Input("false")).getValue());
	}

	@Test
	void test_true() {
		assertEquals("1", MagmaCompiler_.compile(new Input("true")).getValue());
	}

	@Test
	void variable() {
		assertEquals("test", MagmaCompiler_.compile(new Input("test")).getValue());
	}

	@Test
	void while_test() {
		assertEquals("while(1){}", MagmaCompiler_.compile(new Input("while(true){}")).getValue());
	}

	@Test
	void zero() {
		assertEquals("0", MagmaCompiler_.compile(new Input("0")).getValue());
	}
}