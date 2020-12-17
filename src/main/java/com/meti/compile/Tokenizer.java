package com.meti.compile;

import java.util.Optional;

public interface Tokenizer {
	Optional<Node> tokenize(String content);
}
