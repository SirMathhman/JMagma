package com.meti.compile.feature.variable;

import com.meti.compile.lex.LexException;
import com.meti.compile.lex.Lexer;
import com.meti.compile.token.Input;
import com.meti.compile.token.Token;

import java.util.Optional;

public class VariableLexer implements Lexer<Token> {
	public static final Lexer<Token> VariableLexer_ = new VariableLexer();

	public VariableLexer() {
	}

	@Override
	public Optional<Token> lex(Input input) throws LexException {
		return Optional.of(new Variable(input));
	}
}
