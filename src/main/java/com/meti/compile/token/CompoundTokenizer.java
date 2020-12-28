package com.meti.compile.token;

import java.util.List;
import java.util.Optional;

public abstract class CompoundTokenizer<T> implements Tokenizer<T> {
	protected abstract List<Tokenizer<T>> listChildren();

	@Override
	public Optional<T> tokenize(String content) throws TokenizationException {
		var children = listChildren();
		for (Tokenizer<T> child : children) {
			var optional = child.tokenize(content);
			if (optional.isPresent()) {
				return optional;
			}
		}
		return Optional.empty();
	}
}
