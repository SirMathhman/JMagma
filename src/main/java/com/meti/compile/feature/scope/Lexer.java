package com.meti.compile.feature.scope;

public interface Lexer<T> {
	boolean canLex(String line);

	T lex(String content);
}
