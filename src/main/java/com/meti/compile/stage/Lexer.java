package com.meti.compile.stage;

import java.util.Optional;

public interface Lexer<T> {
	Optional<T> lex(String content);
}
