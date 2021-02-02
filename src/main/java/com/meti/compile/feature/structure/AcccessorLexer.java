package com.meti.compile.feature.structure;

import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class AcccessorLexer implements Lexer<Token> {
	public static final AcccessorLexer AccessorLexer_ = new AcccessorLexer();

	private AcccessorLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.contains("=>");
	}

	@Override
	public Token lex(String line) {
		var separator = line.indexOf("=>");
		var firstSlice = line.substring(0, separator);
		var first = firstSlice.trim();
		var structure = MagmaLexingStage_.lexNode(first).render();
		var memberSlice = line.substring(separator + 2);
		var memberString = memberSlice.trim();
		return new Content("%s.%s".formatted(structure, memberString));
	}
}