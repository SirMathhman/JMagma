package com.meti.compile.feature.function;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.content.ParameterSplitter;
import com.meti.compile.feature.scope.Input;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class InvocationLexer implements Lexer<Token> {

	public static final InvocationLexer InvocationLexer_ = new InvocationLexer();

	private InvocationLexer() {
	}

	private boolean canLex(String content) {
		return content.contains("(") && content.endsWith(")");
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent()) ? new Some<>(lex2(input.getContent())) : new None<>();
	}

	private Token lex2(String line) {
		var separator = line.indexOf('(');
		var callerSlice = line.substring(0, separator);
		var callerString = callerSlice.trim();
		var caller = MagmaLexingStage_.lexNode(callerString).render();
		var argumentsSlice = line.substring(separator + 1, line.length() - 1);
		var argumentsString = argumentsSlice.trim();
		List<String> arguments = null;
		try {
			arguments = ParameterSplitter.ParameterSplitter_.stream(argumentsString)
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(line1 -> MagmaLexingStage_.lexNode(line1).render())
					.fold(new ArrayList<>(), JavaLists::add);
		} catch (StreamException e) {
			arguments = new ArrayList<>();
		}
		return new Content(caller + arguments.stream().collect(Collectors.joining(",", "(", ")")));
	}
}