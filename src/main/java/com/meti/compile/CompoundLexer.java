package com.meti.compile;

import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.api.magma.collect.stream.Streams;
import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Input;
import com.meti.compile.token.Token;

public abstract class CompoundLexer implements Lexer<Token> {
	private boolean canLex(String content) {
		return true;
	}

	@Override
	public Option<Token> lex(Input input) {
		try {
			return streamLexers()
					.map(lexer -> lexer.lex(input))
					.flatMap(Streams::ofOption)
					.headOptionally();
		} catch (StreamException e) {
			e.printStackTrace();
			return new None<>();
		}
	}

	protected abstract Stream<Lexer<Token>> streamLexers();
}
