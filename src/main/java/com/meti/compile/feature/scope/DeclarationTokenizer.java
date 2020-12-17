package com.meti.compile.feature.scope;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.NodeTokenizer;

import java.util.Optional;

import static com.meti.compile.feature.field.FieldTokenizer.FieldTokenizer_;

public class DeclarationTokenizer implements NodeTokenizer {
	public static final DeclarationTokenizer DeclarationTokenizer_ = new DeclarationTokenizer();

	private DeclarationTokenizer() {
	}

	@Override
	public Optional<Node> tokenize(String content) {
		return FieldTokenizer_
				.tokenize(content)
				.map(Declaration::new);
	}
}
