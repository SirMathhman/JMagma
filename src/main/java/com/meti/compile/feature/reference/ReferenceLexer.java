package com.meti.compile.feature.reference;

import com.meti.compile.MagmaCompiler;

public class ReferenceLexer {
	public static String compileReference(String line, MagmaCompiler compiler) {
		var slice = line.substring(1);
		var string = slice.trim();
		var node = compiler.compileNode(string);
		return "&%s".formatted(node);
	}

	public static boolean isReference(String line) {
		return line.startsWith("&");
	}
}