package com.meti.compile.feature.scope;

import com.meti.compile.Compiler;

public class DeclarationLexer implements Lexer {
	public static final DeclarationLexer DeclarationLexer_ = new DeclarationLexer();

	private DeclarationLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.contains(":") && line.contains("=");
	}

	@Override
	public String lex(String line, Compiler compiler) {
		return "%s;".formatted(compiler.compileField(line));
	}
}