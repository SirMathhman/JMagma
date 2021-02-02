package com.meti.compile;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import java.util.stream.Stream;

public abstract class CompoundLexer implements Lexer<Token> {
	private boolean canLex(String content) {
		return true;
	}

	@Override
	public Option<Token> lex(String content) {
		return canLex(content) ? new Some<>(lex2(content)) : new None<>();
	}

	private Token lex2(String line) {
		return streamLexers()
				.filter(lexer -> lexer.lex(line).isPresent())
				.map(lexer -> lexer.lex(line).orElse(null))
				.findFirst()
				.orElse(new Content(line));
	}

	protected abstract Stream<Lexer<Token>> streamLexers();
}
