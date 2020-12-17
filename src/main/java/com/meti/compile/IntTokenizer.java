package com.meti.compile;

import java.util.Optional;

public class IntTokenizer implements Tokenizer {
	static final Tokenizer IntTokenizer_ = new IntTokenizer();

	private IntTokenizer() {
	}

	@Override
	public Optional<String> tokenizeToString(String content) {
		try {
			var value = Integer.parseInt(content);
			return Optional.of(String.valueOf(value));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Node> tokenize(String content) {
		return Optional.empty();
	}
}