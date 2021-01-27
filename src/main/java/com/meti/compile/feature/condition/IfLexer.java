package com.meti.compile.feature.condition;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;

public class IfLexer extends ConditionLexer {
	public static final Lexer IfLexer_ = new IfLexer();

	private IfLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("if");
	}

	@Override
	public Content lex(String line, Compiler compiler) {
		return new Content(lex(line, "if", compiler));
	}
}