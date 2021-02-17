package com.meti;

import java.util.Optional;

public interface Lexer<T> {
	Optional<T> lex(Input input) throws LexException;
}
