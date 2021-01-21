package com.meti.compile.stage;

import java.util.Optional;

public class FieldLexer<T> implements Lexer<T> {
	@Override
	public Optional<T> lex(String content) {
		return Optional.empty();
	}
}
