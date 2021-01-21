package com.meti.compile.feature.primitive;

import com.meti.compile.CompileException;
import com.meti.compile.stage.Lexer;
import com.meti.compile.token.Token;

import java.util.Optional;

public class PrimitiveLexer implements Lexer<Token> {
	public static final Lexer<Token> PrimitiveLexer_ = new PrimitiveLexer();

	public PrimitiveLexer() {
	}

	@Override
	public Optional<Token> lex(String content) throws CompileException {
		try {
			return Optional.of(Primitives.valueOf(content));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}
}
