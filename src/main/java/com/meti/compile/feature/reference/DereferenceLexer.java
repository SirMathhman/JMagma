package com.meti.compile.feature.reference;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;

public class DereferenceLexer implements Lexer {
	public static final DereferenceLexer DereferenceLexer_ = new DereferenceLexer();

	private DereferenceLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("*");
	}

	@Override
	public String lex(String line, Compiler compiler) {
		var slice = line.substring(1);
		var string = slice.trim();
		var node = compiler.compileNode(string);
		return "*%s".formatted(node);
	}
}