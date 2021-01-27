package com.meti.compile.feature.reference;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class DereferenceLexer implements Lexer {
	public static final DereferenceLexer DereferenceLexer_ = new DereferenceLexer();

	private DereferenceLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("*");
	}

	@Override
	public Content lex(String line, Compiler compiler) {
		var slice = line.substring(1);
		var string = slice.trim();
		var node = MagmaLexingStage_.lexNode(string, null).getValue();
		return new Content("*%s".formatted(node));
	}
}