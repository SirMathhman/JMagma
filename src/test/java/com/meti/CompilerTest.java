package com.meti;

import org.junit.jupiter.api.Test;

import static com.meti.Compiler.Compiler_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompilerTest {

	@Test
	void empty() throws CompileException {
		assertEquals("", Compiler_.compile(new Input("")).compute());
	}

	@Test
	void test_main() throws CompileException {
		assertEquals("int main(){return 0;}", Compiler_.compile(new Input("def main() : I16 => {return 0;}")).compute());
	}
}