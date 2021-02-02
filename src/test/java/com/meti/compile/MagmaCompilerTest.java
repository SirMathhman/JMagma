package com.meti.compile;

import com.meti.compile.token.Input;
import com.meti.compile.token.Output;
import org.junit.jupiter.api.Test;

import static com.meti.compile.MagmaCompiler.MagmaCompiler_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MagmaCompilerTest {
	@Test
	void assign() {
		assertCompile("x=y;", "x=y;");
	}

	@Test
	void assignment() {
		assertCompile("x=4;", "x=4;");
	}

	@Test
	void block() {
		assertCompile("{}", "{}");
	}

	@Test
	void blockWithChild() {
		assertCompile("{int x=0;}", "{const x : I16 = 0;}");
	}

	@Test
	void blockWithChildren() {
		assertCompile("{int x=0;int y=420;}", """
				{
					const x : I16 = 0;
					const y : I16 = 420;
				}
				""");
	}

	@Test
	void construction() {
		assertCompile("{3,4}", "[Point]{3, 4}");
	}

	@Test
	void declare() {
		assertCompile("int x=10;", "const x : I16 = 10;");
	}

	@Test
	void dereference() {
		assertCompile("*value", "*value");
	}

	@Test
	void doubling() {
		assertCompile("10.0d", "10.0d");
	}

	@Test
	void elif() {
		assertCompile("if(1){}else if(1){}else{}", """
				if(true){
				} elif(true){
				} else {
				}
				""");
	}

	@Test
	void else_if() {
		assertCompile("if(1){}else{}", "if(true){}else{}");
	}

	@Test
	void else_test() {
		assertCompile("else{}", "else {}");
	}

	@Test
	void floating() {
		assertCompile("10.0", "10.0");
	}

	@Test
	void function() {
		assertCompile("int main(){return 0;}", "def main() : I16 => {return 0;}");
	}

	@Test
	void invoke() {
		assertCompile("main(test)", "main(test)");
	}

	@Test
	void member() {
		assertCompile("myPoint.x", "myPoint => x");
	}

	@Test
	void negativeInt() {
		assertCompile("-10", "-10");
	}

	@Test
	void positiveInt() {
		assertCompile("10", "10");
	}

	@Test
	void quantity() {
		assertCompile("(4)", "(4)");
	}

	@Test
	void reference() {
		assertCompile("&value", "&value");
	}

	@Test
	void returns() {
		assertCompile("return 420;", "return 420;");
	}

	@Test
	void structure() {
		assertCompile("struct Wrapper{int value;};", "struct Wrapper{const value : I16}");
	}

	@Test
	void test_false() {
		assertCompile("0", "false");
	}

	@Test
	void test_true() {
		assertCompile("1", "true");
	}

	private void assertCompile(String expected, String source) {
		Output result;
		try {
			result = MagmaCompiler_.compile(new Input(source));
		} catch (CompileException e) {
			e.printStackTrace();
			result = new Output("");
		}
		assertEquals(expected, result.asString());
	}

	@Test
	void variable() {
		assertCompile("test", "test");
	}

	@Test
	void while_test() {
		assertCompile("while(1){}", "while(true){}");
	}

	@Test
	void zero() {
		assertCompile("0", "0");
	}
}