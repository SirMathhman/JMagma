package com.meti.compile.feature;

import java.util.Optional;

public interface Tokenizer<T> {
	Optional<T> tokenize(String content);
}
