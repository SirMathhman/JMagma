package com.meti.compile.feature.structure;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class AcccessorLexer implements Lexer<Token> {
	public static final AcccessorLexer AccessorLexer_ = new AcccessorLexer();

	private AcccessorLexer() {
	}

	private boolean canLex(String content) {
		return content.contains("=>");
	}

	@Override
	public Option<Token> lex(String content) {
		return canLex(content) ? new Some<>(lex2(content)) : new None<>();
	}

	private Token lex2(String line) {
		var separator = line.indexOf("=>");
		var firstSlice = line.substring(0, separator);
		var first = firstSlice.trim();
		var structure = MagmaLexingStage_.lexNode(first).render();
		var memberSlice = line.substring(separator + 2);
		var memberString = memberSlice.trim();
		return new Content("%s.%s".formatted(structure, memberString));
	}
}