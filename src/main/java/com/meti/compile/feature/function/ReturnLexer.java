package com.meti.compile.feature.function;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;

public class ReturnLexer implements Lexer {
	public static final ReturnLexer ReturnLexer_ = new ReturnLexer();

	private ReturnLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("return ");
	}

	@Override
	public String lex(String line, Compiler compiler) {
		var valueSlice = line.substring(7);
		var valueString = valueSlice.trim();
		var value = compiler.compileNode(valueString);
		return "return %s;".formatted(value);
	}
}