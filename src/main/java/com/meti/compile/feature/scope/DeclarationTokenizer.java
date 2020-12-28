package com.meti.compile.feature.scope;

import com.meti.compile.TokenizationException;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.Tokenizer;

import java.util.Optional;

import static com.meti.compile.feature.field.FieldTokenizer.FieldTokenizer_;

public class DeclarationTokenizer implements Tokenizer<Node> {
	public static final DeclarationTokenizer DeclarationTokenizer_ = new DeclarationTokenizer();

	private DeclarationTokenizer() {
	}

	@Override
	public Optional<Node> tokenize(String content) throws TokenizationException {
		return FieldTokenizer_
				.tokenize(content)
				.map(Declaration::new);
	}
}
