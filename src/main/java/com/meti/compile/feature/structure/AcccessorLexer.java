package com.meti.compile.feature.structure;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;

public class AcccessorLexer implements Lexer {
	public static final AcccessorLexer AccessorLexer_ = new AcccessorLexer();

	private AcccessorLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.contains("=>");
	}

	@Override
	public String lex(String line, Compiler compiler) {
		var separator = line.indexOf("=>");
		var firstSlice = line.substring(0, separator);
		var first = firstSlice.trim();
		var structure = compiler.compileNode(first);
		var memberSlice = line.substring(separator + 2);
		var memberString = memberSlice.trim();
		return "%s.%s".formatted(structure, memberString);
	}
}