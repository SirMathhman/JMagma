package com.meti.compile.feature.reference;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class ReferenceLexer implements Lexer {
	public static final ReferenceLexer ReferenceLexer_ = new ReferenceLexer();

	private ReferenceLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("&");
	}

	@Override
	public Token lex(String line) {
		var slice = line.substring(1);
		var string = slice.trim();
		var node = MagmaLexingStage_.lexNode(string).render();
		return new Content("&%s".formatted(node));
	}
}