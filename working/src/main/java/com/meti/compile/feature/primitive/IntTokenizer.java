package com.meti.compile.feature.primitive;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.Tokenizer;

import java.math.BigInteger;
import java.util.Optional;

import static com.meti.compile.feature.primitive.Int.Int;

public class IntTokenizer implements Tokenizer<Node> {
	public static final Tokenizer<Node> IntTokenizer_ = new IntTokenizer();
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