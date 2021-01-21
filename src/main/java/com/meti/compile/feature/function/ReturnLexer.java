package com.meti.compile.feature.function;

import com.meti.compile.CompileException;
import com.meti.compile.stage.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import java.util.Optional;

public class ReturnLexer implements Lexer<Token> {
	public static final Lexer<Token> ReturnLexer_ = new ReturnLexer();

	public ReturnLexer() {
	}

	@Override
	public Optional<Token> lex(String content) throws CompileException {
		if (content.startsWith("return ")) {
			var valueSlice = content.substring(7);
			var valueString = valueSlice.trim();
			var value = new Content(valueString);
			var node = new Return(value);
			return Optional.of(node);
		}
		return Optional.empty();
	}
}
