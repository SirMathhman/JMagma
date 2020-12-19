package com.meti.compile.feature.primitive;

import com.meti.compile.feature.Tokenizer;
import com.meti.compile.feature.Type;

import java.util.Optional;

public class PrimitiveTokenizer implements Tokenizer<Type> {
	public static final Tokenizer<Type> PrimitiveTokenizer_ = new PrimitiveTokenizer();

	private PrimitiveTokenizer() {
	}

	@Override
	public Optional<Type> tokenize(String content) {
		try {
			return Optional.of(Primitive.valueOf(content));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}
}
