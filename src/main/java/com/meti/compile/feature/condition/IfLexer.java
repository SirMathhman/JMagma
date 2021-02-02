package com.meti.compile.feature.condition;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

public class IfLexer extends ConditionLexer {
	public static final Lexer<Token> IfLexer_ = new IfLexer();

	private IfLexer() {
	}

	@Override
	public Option<Token> lex(String content) {
		return canLex(content, "if") ?
				new Some<>(new Content(lex(content, "if"))) :
				new None<>();
	}
}