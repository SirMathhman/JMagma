package com.meti.compile.feature;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class CompoundTokenizer<T> implements Tokenizer<T> {
	protected abstract Stream<Tokenizer<T>> streamChildren();

	@Override
	public Optional<T> tokenize(String content) {
		return streamChildren()
				.map(tokenizer -> tokenizer.tokenize(content))
				.flatMap(Optional::stream)
				.findFirst();
	}
}
