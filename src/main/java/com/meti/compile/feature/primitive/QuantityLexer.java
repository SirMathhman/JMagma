package com.meti.compile.feature.primitive;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;

public class QuantityLexer implements Lexer {
	public static final QuantityLexer QuantityLexer_ = new QuantityLexer();

	private QuantityLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("(") && line.endsWith(")");
	}

	@Override
	public String lex(String line, Compiler compiler) {
		var slice = line.substring(1, line.length() - 1);
		var string = slice.trim();
		var node = compiler.compileNode(string);
		return "(%s)".formatted(node);
	}
}