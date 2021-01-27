package com.meti.compile.feature.scope;

import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class DeclarationLexer implements Lexer {
	public static final DeclarationLexer DeclarationLexer_ = new DeclarationLexer();

	private DeclarationLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.contains(":") && line.contains("=");
	}

	@Override
	public Token lex(String line) {
		return new Content("%s;".formatted(MagmaLexingStage_.lexField(line).render()));
	}
}