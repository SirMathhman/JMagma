package com.meti.compile.feature.scope;

import com.meti.compile.MagmaCompiler;

public class BlockLexer {
	public static String compileBlock(String line, MagmaCompiler compiler) {
		var bodySlice = line.substring(1, line.length() - 1);
		var bodyString = bodySlice.trim();
		var body = compiler.compileLines(bodyString);
		return "{%s}".formatted(body);
	}

	public static boolean isBlock(String line) {
		return line.startsWith("{") && line.endsWith("}");
	}
}