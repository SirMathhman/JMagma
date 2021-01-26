package com.meti.compile.feature.scope;

import com.meti.compile.Compiler;

public class AssignmentLexer {
	public static String compileAssignment(String line, Compiler compiler) {
		var separator = line.indexOf('=');
		var beforeSlice = line.substring(0, separator);
		var beforeString = beforeSlice.trim();
		var before = compiler.compileNode(beforeString);
		var afterSlice = line.substring(separator + 1);
		var afterString = afterSlice.trim();
		var after = compiler.compileNode(afterString);
		return "%s=%s;".formatted(before, after);
	}

	public static boolean isAssignment(String line) {
		return line.contains("=");
	}
}