package com.meti.compile.feature.scope;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.Tokenizer;

import java.util.Optional;

import static com.meti.compile.feature.scope.Variable.Variable;

public class VariableTokenizer implements Tokenizer<Node> {
	@Override
	public Optional<Node> tokenize(String content) {
		return Optional.of(Variable(content));
	}
}
