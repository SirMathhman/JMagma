package com.meti;

import org.junit.jupiter.api.Test;

import static com.meti.Compiler.Compiler_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompilerTest {

	@Test
	void empty() {
		assertThrows(CompileException.class, () -> Compiler_.compile(""));
	}

	@Test
	void test_main() throws CompileException {
		assertEquals("int main(){return 0;}", Compiler_.compile("def main() : I16 => {return 0;}"));
	}
}