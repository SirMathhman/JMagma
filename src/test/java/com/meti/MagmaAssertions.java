package com.meti;

import com.meti.compile.CompileException;
import com.meti.compile.token.RootInput;

import static com.meti.compile.app.MagmaCompiler.MagmaCompiler_;
import static org.junit.jupiter.api.Assertions.*;

public class MagmaAssertions {
	public MagmaAssertions() {
	}

	public static void assertCompile(String from, String to) {
		try {
			String result = compileImpl(from);
			assertEquals(to, result);
		} catch (CompileException e) {
			fail(e);
		}
	}

	private static String compileImpl(String source) throws CompileException {
		var input = new RootInput(source);
		return MagmaCompiler_.compile(input);
	}

	public static void assertCompileThrows(String source) {
		assertThrows(CompileException.class, () -> compileImpl(source));
	}
}
