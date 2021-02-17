package com.meti;

import java.util.Optional;

import static com.meti.FieldLexer.FieldLexer_;

public class DeclarationLexer implements Lexer<Token> {
	public static final Lexer<Token> DeclarationLexer_ = new DeclarationLexer();

	public DeclarationLexer() {
	}

	@Override
	public Optional<Token> lex(Input input) throws LexException {
		return FieldLexer_.lex(input).map(Declaration::new);
	}
}
