package com.meti.compile.feature.reference;

import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class DereferenceLexer implements Lexer<Token> {
	public static final DereferenceLexer DereferenceLexer_ = new DereferenceLexer();

	private DereferenceLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("*");
	}

	@Override
	public Token lex(String line) {
		var slice = line.substring(1);
		var string = slice.trim();
		var node = MagmaLexingStage_.lexNode(string).render();
		return new Content("*%s".formatted(node));
	}
}