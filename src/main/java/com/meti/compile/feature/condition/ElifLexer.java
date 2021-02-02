package com.meti.compile.feature.condition;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.feature.scope.Input;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

public class ElifLexer extends ConditionLexer {
	public static final Lexer<Token> ElifLexer_ = new ElifLexer();

	private ElifLexer() {
	}

	@Override
	public Option<Token> lex(Input input) {
		return canLex(input.getContent(), "elif") ?
				new Some<>(new Content(lex(input.getContent(), "else if"))) :
				new None<>();
	}
}