package com.meti.compile.feature.primitive;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;

public class BooleanLexer implements Lexer {
	public static final BooleanLexer BooleanLexer_ = new BooleanLexer();

	private BooleanLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.equals("true") || line.equals("false");
	}

	@Override
	public Content lex(String line, Compiler compiler) {
		return new Content(line.equals("true") ? "1" : "0");
	}
}