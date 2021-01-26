package com.meti.compile.feature.structure;

import com.meti.compile.MagmaCompiler;

public class AcccessorLexer {
	public static String compileAccesor(String line, MagmaCompiler compiler) {
		var separator = line.indexOf("=>");
		var firstSlice = line.substring(0, separator);
		var first = firstSlice.trim();
		var structure = compiler.compileNode(first);
		var memberSlice = line.substring(separator + 2);
		var memberString = memberSlice.trim();
		return "%s.%s".formatted(structure, memberString);
	}

	public static boolean isAccessor(String line) {
		return line.contains("=>");
	}
}