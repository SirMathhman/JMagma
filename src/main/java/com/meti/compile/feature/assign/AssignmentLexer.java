package com.meti.compile.feature.assign;

import com.meti.compile.lex.LexException;
import com.meti.compile.lex.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Input;
import com.meti.compile.token.Token;

import java.util.Optional;

public class AssignmentLexer implements Lexer<Token> {
	public static final Lexer<Token> AssignmentLexer_ = new AssignmentLexer();

	public AssignmentLexer() {
	}

	@Override
	public Optional<Token> lex(Input input) throws LexException {
		return input.firstChar('=')
				.map(equals -> {
					var before = input.slice(0, equals);
					var after = input.slice(equals + 1);
					var beforeNode = new Content(before);
					var afterNode = new Content(after);
					return new Assignment(beforeNode, afterNode);
				});
	}
}
