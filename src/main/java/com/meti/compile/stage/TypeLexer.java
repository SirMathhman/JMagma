package com.meti.compile.stage;

import com.meti.compile.token.Token;

import java.util.stream.Stream;

public class TypeLexer extends CompoundLexer<Token> {
	public static final Lexer<Token> TypeLexer_ = new TypeLexer();

	private TypeLexer() {
	}

	@Override
	protected Stream<Lexer<Token>> streamLexers() {
		return Stream.empty();
	}
}
