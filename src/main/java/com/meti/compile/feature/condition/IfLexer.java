package com.meti.compile.feature.condition;

import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

public class IfLexer extends ConditionLexer {
	public static final Lexer<Token> IfLexer_ = new IfLexer();

	private IfLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("if");
	}

	@Override
	public Token lex(String line) {
		return new Content(lex(line, "if"));
	}
}