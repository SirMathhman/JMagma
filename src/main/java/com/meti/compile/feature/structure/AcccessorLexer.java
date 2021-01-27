package com.meti.compile.feature.structure;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class AcccessorLexer implements Lexer {
	public static final AcccessorLexer AccessorLexer_ = new AcccessorLexer();

	private AcccessorLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.contains("=>");
	}

	@Override
	public Content lex(String line, Compiler compiler) {
		var separator = line.indexOf("=>");
		var firstSlice = line.substring(0, separator);
		var first = firstSlice.trim();
		var structure = MagmaLexingStage_.lexNode(first, null).getValue();
		var memberSlice = line.substring(separator + 2);
		var memberString = memberSlice.trim();
		return new Content("%s.%s".formatted(structure, memberString));
	}
}