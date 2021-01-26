package com.meti.compile.feature.structure;

import com.meti.compile.MagmaCompiler;

import java.util.stream.Collectors;

public class ConstructionLexer {
	public static String compileConstruction(String line, MagmaCompiler compiler) {
		var bodyStart = line.indexOf('{');
		var bodySlice = line.substring(bodyStart + 1, line.length() - 1);
		var bodyString = bodySlice.trim();
		var arguments = MagmaCompiler.splitSequence(bodyString)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(compiler::compileNode)
				.collect(Collectors.toList());
		return arguments.stream().collect(Collectors.joining(",", "{", "}"));
	}

	public static boolean isConstruction(String line) {
		return line.startsWith("[") && line.contains("]") &&
		       line.contains("{") && line.contains("}");
	}
}