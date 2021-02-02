package com.meti.compile.feature.scope;

import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class AssignmentLexer implements Lexer<Token> {
	public static final AssignmentLexer AssignmentLexer_ = new AssignmentLexer();

	private AssignmentLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.contains("=");
	}

	@Override
	public Token lex(String line) {
		var separator = line.indexOf('=');
		var beforeSlice = line.substring(0, separator);
		var beforeString = beforeSlice.trim();
		var before = MagmaLexingStage_.lexNode(beforeString).render();
		var afterSlice = line.substring(separator + 1);
		var afterString = afterSlice.trim();
		var after = MagmaLexingStage_.lexNode(afterString).render();
		return new Content("%s=%s;".formatted(before, after));
	}
}