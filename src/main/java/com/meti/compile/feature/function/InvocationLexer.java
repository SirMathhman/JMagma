package com.meti.compile.feature.function;

import com.meti.compile.MagmaCompiler;

import java.util.stream.Collectors;

public class InvocationLexer {
	public static String compileInvocation(String line, MagmaCompiler compiler) {
		var separator = line.indexOf('(');
		var callerSlice = line.substring(0, separator);
		var callerString = callerSlice.trim();
		var caller = compiler.compileNode(callerString);
		var argumentsSlice = line.substring(separator + 1, line.length() - 1);
		var argumentsString = argumentsSlice.trim();
		var arguments = MagmaCompiler.splitSequence(argumentsString)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(compiler::compileNode)
				.collect(Collectors.toList());
		return caller + arguments.stream().collect(Collectors.joining(",", "(", ")"));
	}

	public static boolean isInvocation(String line) {
		return line.contains("(") && line.endsWith(")");
	}
}