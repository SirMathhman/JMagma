package com.meti.compile.tokenize;

import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.api.core.Some;

import java.util.Optional;

public interface Tokenizer<T> {
	default Option<T> tokenizeImpl() {
		return tokenize()
				.map(Some::Some)
				.orElse(None.None());
	}

	Optional<T> tokenize();
}
