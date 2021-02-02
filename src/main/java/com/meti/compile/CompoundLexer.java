package com.meti.compile;

import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import java.util.stream.Stream;

public abstract class CompoundLexer implements Lexer<Token> {
	@Override
	public boolean canLex(String line) {
		return true;
	}

	@Override
	public Token lex(String line) {
		return streamLexers()
				.filter(lexer -> lexer.canLex(line))
				.map(lexer -> lexer.lex(line))
				.findFirst()
				.orElse(new Content(line));
	}

	protected abstract Stream<Lexer<Token>> streamLexers();
}
