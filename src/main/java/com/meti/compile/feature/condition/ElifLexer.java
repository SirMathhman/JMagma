package com.meti.compile.feature.condition;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;

public class ElifLexer extends ConditionLexer {
	public static final Lexer ElifLexer_ = new ElifLexer();

	private ElifLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("elif");
	}

	@Override
	public String lex(String line, Compiler compiler) {
		return compileCondition(line, "else if", compiler);
	}
}