package com.meti.compile.lex;

import com.meti.compile.token.Input;

import java.util.Optional;

public interface Lexer<T> {
	Optional<T> lex(Input input) throws LexException;
}
