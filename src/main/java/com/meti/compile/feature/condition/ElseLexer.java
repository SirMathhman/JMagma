package com.meti.compile.feature.condition;

import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class ElseLexer implements Lexer<Token> {
	public static final Lexer<Token> ElseLexer_ = new ElseLexer();

	private ElseLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("else");
	}

	@Override
	public Token lex(String line) {
		var bodySlice = line.substring(4);
		var bodyString = bodySlice.trim();
		return new Content("else%s".formatted(MagmaLexingStage_.lexNode(bodyString).render()));
	}
}
