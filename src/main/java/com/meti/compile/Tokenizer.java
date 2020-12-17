package com.meti.compile;

import java.util.Optional;

public interface Tokenizer {
	Optional<String> tokenize(String content);
}
