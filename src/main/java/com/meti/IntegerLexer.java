package com.meti;

import java.util.Optional;

public class IntegerLexer implements Lexer<Token> {
	public static final Lexer<Token> IntegerLexer_ = new IntegerLexer();

	public IntegerLexer() {
	}

	@Override
	public Optional<Token> lex(Input input) throws LexException {
		if (input.stream().allMatch(Character::isDigit)) {
			return Optional.of(new Integer(input.peek(java.lang.Integer::parseInt)));
		}
		return Optional.empty();
	}
}
