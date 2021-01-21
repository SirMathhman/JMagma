package com.meti.compile.stage;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class CompoundLexer<T> implements Lexer<T> {
	@Override
	public Optional<T> lex(String content) {
		return streamLexers()
				.map(lexer -> lexer.lex(content))
				.flatMap(Optional::stream)
				.findFirst();
	}

	protected abstract Stream<Lexer<T>> streamLexers();
}
