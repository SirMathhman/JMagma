package com.meti.compile.feature.condition;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.feature.scope.Input;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

public class WhileLexer extends ConditionLexer {
	public static final Lexer<Token> WhileLexer_ = new WhileLexer();

	private WhileLexer() {
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent(), "while") ?
				new Some<>(new Content(lex(input.getContent(), "while"))) :
				new None<>();
	}
}