package com.meti.compile;

import java.math.BigInteger;
import java.util.Optional;

import static com.meti.compile.Int.Int;

public class IntTokenizer implements Tokenizer {
	static final Tokenizer IntTokenizer_ = new IntTokenizer();
	private static final int Base10 = 10;

	private IntTokenizer() {
	}

	@Override
	public Optional<Node> tokenize(String content) {
		try {
			return Optional.of(Int(new BigInteger(content, Base10)));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
}