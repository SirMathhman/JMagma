package com.meti.compile.feature.function;

import com.meti.compile.Compiler;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;

import static com.meti.compile.MagmaLexingStage.MagmaLexingStage_;

public class ReturnLexer implements Lexer {
	public static final ReturnLexer ReturnLexer_ = new ReturnLexer();

	private ReturnLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("return ");
	}

	@Override
	public Content lex(String line, Compiler compiler) {
		var valueSlice = line.substring(7);
		var valueString = valueSlice.trim();
		var value = MagmaLexingStage_.lexNode(valueString, null).getValue();
		return new Content("return %s;".formatted(value));
	}
}