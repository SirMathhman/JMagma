package com.meti.compile.feature.condition;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class ElseLexer implements Lexer<Token> {
	public static final Lexer<Token> ElseLexer_ = new ElseLexer();

	private ElseLexer() {
	}

	private boolean canLex(String content) {
		return content.startsWith("else");
	}

	@Override
	public Option<Token> lex(String content) {
		return canLex(content) ? new Some<>(lex2(content)) : new None<>();
	}

	private Token lex2(String line) {
		var bodySlice = line.substring(4);
		var bodyString = bodySlice.trim();
		return new Content("else%s".formatted(MagmaLexingStage_.lexNode(bodyString).render()));
	}
}
