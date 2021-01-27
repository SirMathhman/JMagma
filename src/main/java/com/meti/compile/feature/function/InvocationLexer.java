package com.meti.compile.feature.function;

import com.meti.compile.Compiler;
import com.meti.compile.MagmaCompiler;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;

import java.util.stream.Collectors;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class InvocationLexer implements Lexer {

	public static final InvocationLexer InvocationLexer_ = new InvocationLexer();

	private InvocationLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.contains("(") && line.endsWith(")");
	}

	@Override
	public Content lex(String line, Compiler compiler) {
		var separator = line.indexOf('(');
		var callerSlice = line.substring(0, separator);
		var callerString = callerSlice.trim();
		var caller = MagmaLexingStage_.lexNode(callerString, null).getValue();
		var argumentsSlice = line.substring(separator + 1, line.length() - 1);
		var argumentsString = argumentsSlice.trim();
		var arguments = MagmaCompiler.splitSequence(argumentsString)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(line1 -> MagmaLexingStage_.lexNode(line1, null).getValue())
				.collect(Collectors.toList());
		return new Content(caller + arguments.stream().collect(Collectors.joining(",", "(", ")")));
	}
}