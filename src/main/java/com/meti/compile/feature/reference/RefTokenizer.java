package com.meti.compile.feature.reference;

import com.meti.compile.TokenizationException;
import com.meti.compile.feature.Tokenizer;
import com.meti.compile.feature.Type;

import java.util.Optional;

import static com.meti.compile.feature.content.ContentType.ContentType;
import static com.meti.compile.feature.reference.RefType.RefType;

public class RefTokenizer implements Tokenizer<Type> {
	public static final Tokenizer<Type> RefTokenizer_ = new RefTokenizer();

	public RefTokenizer() {
	}

	@Override
	public Optional<Type> tokenize(String content) {
		if (content.startsWith("Ref")) {
			var start = content.indexOf('[');
			var end = content.indexOf(']');
			var slice = content.substring(start + 1, end);
			var trim = slice.trim();
			var child = ContentType(trim);
			return Optional.of(RefType(child));
		}
		return Optional.empty();
	}
}
