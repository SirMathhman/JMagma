package com.meti.compile.feature.scope;

import com.meti.compile.MagmaCompiler;

public class DeclarationLexer {
	public static String compileDeclaration(String line, MagmaCompiler compiler) {
		return "%s;".formatted(compiler.compileField(line));
	}

	public static boolean isDeclaration(String line) {
		return line.contains(":") && line.contains("=");
	}
}