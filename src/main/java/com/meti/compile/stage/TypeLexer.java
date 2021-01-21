package com.meti.compile.stage;

import com.meti.compile.token.Token;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class TypeLexer extends CompoundLexer<Token> {
	public static final Lexer<Token> TypeLexer_ = new TypeLexer();

	private TypeLexer() {
	}

	@Override
	protected List<Lexer<Token>> listLexers() {
		return emptyList();
	}
}
