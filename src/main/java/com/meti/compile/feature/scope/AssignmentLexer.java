package com.meti.compile.feature.scope;

import com.meti.compile.Compiler;
import com.meti.compile.token.Content;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class AssignmentLexer implements Lexer {
	public static final AssignmentLexer AssignmentLexer_ = new AssignmentLexer();

	private AssignmentLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.contains("=");
	}

	@Override
	public Content lex(String line, Compiler compiler) {
		var separator = line.indexOf('=');
		var beforeSlice = line.substring(0, separator);
		var beforeString = beforeSlice.trim();
		var before = MagmaLexingStage_.lexNode(beforeString, null).getValue();
		var afterSlice = line.substring(separator + 1);
		var afterString = afterSlice.trim();
		var after = MagmaLexingStage_.lexNode(afterString, null).getValue();
		return new Content("%s=%s;".formatted(before, after));
	}
}