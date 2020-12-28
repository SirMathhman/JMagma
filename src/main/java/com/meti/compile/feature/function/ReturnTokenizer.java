package com.meti.compile.feature.function;

import com.meti.compile.token.Node;
import com.meti.compile.token.Tokenizer;

import java.util.Optional;

import static com.meti.compile.feature.content.ContentNode.ContentNode;
import static com.meti.compile.feature.function.Return.Return;

public class ReturnTokenizer implements Tokenizer<Node> {
	public static final Tokenizer<Node> ReturnTokenizer_ = new ReturnTokenizer();

	private ReturnTokenizer() {
	}

	@Override
	public Optional<Node> tokenize(String content) {
		if(content.startsWith("return ")) {
			var slice = content.substring(7);
			var trim = slice.trim();
			var node = ContentNode(trim);
			return Optional.of(Return(node));
		}
		return Optional.empty();
	}
}
