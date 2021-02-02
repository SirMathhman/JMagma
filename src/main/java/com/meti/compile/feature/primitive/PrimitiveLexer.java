package com.meti.compile.feature.primitive;

import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

public class PrimitiveLexer implements Lexer<Token> {
	public static final Lexer<Token> PrimitiveLexer_ = new PrimitiveLexer();

	private PrimitiveLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.equals("I16");
	}

	@Override
	public Token lex(String content) {
		return new Content("int");
	}
}
