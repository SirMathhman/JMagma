package com.meti.compile.feature;

import com.meti.compile.TokenizationException;

import java.util.Optional;

public interface Tokenizer<T> {
	Optional<T> tokenize(String content) throws TokenizationException;
}
