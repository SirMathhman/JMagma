package com.meti.compile.feature.scope;

import com.meti.compile.Compiler;
import com.meti.compile.token.Content;

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
	public Content lex(String line, Compiler compiler) {
		return new Content("%s;".formatted(MagmaLexingStage_.lexField(line)));
	}
}