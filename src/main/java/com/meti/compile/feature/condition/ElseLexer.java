package com.meti.compile.feature.condition;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class ElseLexer implements Lexer {
	public static final Lexer ElseLexer_ = new ElseLexer();

	private ElseLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("else");
	}

	@Override
	public Content lex(String line, Compiler compiler) {
		var bodySlice = line.substring(4);
		var bodyString = bodySlice.trim();
		return new Content("else%s".formatted(MagmaLexingStage_.lexNode(bodyString, compiler).getValue()));
	}
}
