package com.meti.compile.feature.scope;

import com.meti.compile.Compiler;

public class AssignmentLexer implements Lexer {
	public static final AssignmentLexer AssignmentLexer_ = new AssignmentLexer();

	private AssignmentLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.contains("=");
	}

	@Override
	public String lex(String line, Compiler compiler) {
		var separator = line.indexOf('=');
		var beforeSlice = line.substring(0, separator);
		var beforeString = beforeSlice.trim();
		var before = compiler.compileNode(beforeString);
		var afterSlice = line.substring(separator + 1);
		var afterString = afterSlice.trim();
		var after = compiler.compileNode(afterString);
		return "%s=%s;".formatted(before, after);
	}
}