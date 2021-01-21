package com.meti.compile.feature.condition;

import com.meti.compile.TokenizationException;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.Tokenizer;

import java.util.Optional;

import static com.meti.compile.feature.condition.Boolean.Boolean;

public class BooleanTokenizer implements Tokenizer<Node> {
	public static final Tokenizer<Node> BooleanTokenizer_ = new BooleanTokenizer();

	private BooleanTokenizer() {
	}

	@Override
	public Optional<Node> tokenize(String content) throws TokenizationException {
		if (content.equals("true") || content.equals("false")) {
			return Optional.of(Boolean(content.equals("true")));
		}
		return Optional.empty();
	}
}
