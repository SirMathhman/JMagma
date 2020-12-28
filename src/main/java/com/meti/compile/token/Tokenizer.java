package com.meti.compile.token;

import java.util.Optional;

public interface Tokenizer<T> {
	Optional<T> tokenize(String content) throws TokenizationException;
}
