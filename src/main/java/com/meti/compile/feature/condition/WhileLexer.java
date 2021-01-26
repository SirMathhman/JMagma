package com.meti.compile.feature.condition;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;

public class WhileLexer extends ConditionLexer {
	public static final Lexer WhileLexer_ = new WhileLexer();

	private WhileLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("while");
	}

	@Override
	public String lex(String line, Compiler compiler) {
		return compileCondition(line, "while", compiler);
	}
}