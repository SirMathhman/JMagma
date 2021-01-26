package com.meti.compile.feature.primitive;

import com.meti.compile.Compiler;

public class QuantityLexer {
	public static String compileQuantity(String line, Compiler compiler) {
		var slice = line.substring(1, line.length() - 1);
		var string = slice.trim();
		var node = compiler.compileNode(string);
		return "(%s)".formatted(node);
	}

	public static boolean isQuantity(String line) {
		return line.startsWith("(") && line.endsWith(")");
	}
}