package com.meti.compile.feature.condition;

import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

public class ElifLexer extends ConditionLexer {
	public static final Lexer<Token> ElifLexer_ = new ElifLexer();

	private ElifLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("elif");
	}

	@Override
	public Token lex(String line) {
		return new Content(lex(line, "else if"));
	}
}