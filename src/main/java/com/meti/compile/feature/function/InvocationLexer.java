package com.meti.compile.feature.function;

import com.meti.compile.Compiler;
import com.meti.compile.MagmaCompiler;
import com.meti.compile.feature.scope.Lexer;

import java.util.stream.Collectors;

public class InvocationLexer implements Lexer {

	public static final InvocationLexer InvocationLexer_ = new InvocationLexer();

	private InvocationLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.contains("(") && line.endsWith(")");
	}

	@Override
	public String lex(String line, Compiler compiler) {
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
}