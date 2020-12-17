package com.meti.compile;

import java.util.Optional;

public interface Tokenizer<T> {
	Optional<T> tokenize(String content);
}
