package com.meti.compile.feature.condition;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.token.Input;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

public class IfLexer extends ConditionLexer {
	public static final Lexer<Token> IfLexer_ = new IfLexer();

	private IfLexer() {
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent(), "if") ?
				Some.Some(new Content(lex(input.getContent(), "if"))) :
				new None<>();
	}
}