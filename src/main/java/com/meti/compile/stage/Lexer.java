package com.meti.compile.stage;

import com.meti.compile.CompileException;

import java.util.Optional;

public interface Lexer<T> {
	Optional<T> lex(String content) throws CompileException;
}
