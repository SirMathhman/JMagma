package com.meti.lex;

import com.meti.token.Input;

import java.util.Optional;

public interface Lexer<T> {
	Optional<T> lex(Input input) throws LexException;
}
