package com.meti.compile.feature.condition;

import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

public class WhileLexer extends ConditionLexer {
	public static final Lexer<Token> WhileLexer_ = new WhileLexer();

	private WhileLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("while");
	}

	@Override
	public Token lex(String line) {
		return new Content(lex(line, "while"));
	}
}