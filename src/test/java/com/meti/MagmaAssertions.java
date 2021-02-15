package com.meti;

import static com.meti.Compiler.Compiler_;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MagmaAssertions {
	static void assertCompile(String expected, String source) throws CompileException {
		assertEquals(expected, Compiler_.compile(new Input(source)));
	}
}
