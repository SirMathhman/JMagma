package com.meti.compile.feature.primitive;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.token.Input;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

public class BooleanLexer implements Lexer<Token> {
	public static final BooleanLexer BooleanLexer_ = new BooleanLexer();

	private BooleanLexer() {
	}

	private boolean canLex(String content) {
		return content.equals("true") || content.equals("false");
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent()) ? new Some<>(lex2(input.getContent())) : new None<>();
	}

	private Token lex2(String line) {
		return new Content(line.equals("true") ? "1" : "0");
	}
}