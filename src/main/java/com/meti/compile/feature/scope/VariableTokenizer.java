package com.meti.compile.feature.scope;

import com.meti.compile.token.Node;
import com.meti.compile.token.Tokenizer;

import java.util.Optional;

import static com.meti.compile.feature.scope.Variable.Variable;

public class VariableTokenizer implements Tokenizer<Node> {
	public static final VariableTokenizer VariableTokenizer_ = new VariableTokenizer();

	private VariableTokenizer() {
	}

	@Override
	public Optional<Node> tokenize(String content) {
		return Optional.of(Variable(content));
	}
}
