package com.meti.compile.feature.function;

import com.meti.compile.MagmaCompiler;

public class ReturnLexer {
	public static String compileReturn(String line, MagmaCompiler compiler) {
		var valueSlice = line.substring(7);
		var valueString = valueSlice.trim();
		var value = compiler.compileNode(valueString);
		return "return %s;".formatted(value);
	}

	public static boolean isReturn(String line) {
		return line.startsWith("return ");
	}
}