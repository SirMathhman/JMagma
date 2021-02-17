package com.meti.compile.app;

import com.meti.compile.CompileException;
import com.meti.compile.token.RootInput;
import org.junit.jupiter.api.Test;

import static com.meti.compile.app.MagmaCompiler.MagmaCompiler_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MagmaCompilerTest {
	@Test
	void compile() throws CompileException {
		var input = new RootInput("const x : I16 = 420;");
		var result = MagmaCompiler_.compile(input);
		assertEquals("signed int x=420;", result);
	}

	@Test
	void compile_multiple() throws CompileException {
		var input = new RootInput("const x : I16 = 420;const y : I16 = 420;");
		var result = MagmaCompiler_.compile(input);
		assertEquals("signed int x=420;signed int y=420;", result);
	}
}