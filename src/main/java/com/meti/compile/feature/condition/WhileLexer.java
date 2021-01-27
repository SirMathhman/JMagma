package com.meti.compile.feature.condition;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;

public class WhileLexer extends ConditionLexer {
	public static final Lexer WhileLexer_ = new WhileLexer();

	private WhileLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("while");
	}

	@Override
	public Content lex(String line, Compiler compiler) {
		return new Content(lex(line, "while", compiler));
	}
}