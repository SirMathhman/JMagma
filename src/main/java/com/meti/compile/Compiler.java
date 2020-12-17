package com.meti.compile;

import java.util.function.Supplier;

public class Compiler {
	private final Tokenizer<Node> tokenizer = IntTokenizer.IntTokenizer_;

	String compile(String content) throws CompileException {
		Supplier<CompileException> invalidContent = () -> new CompileException("Unable to tokenize content: " + content);
		return tokenizer.tokenize(content)
				.map(Node::render)
				.orElseThrow(invalidContent);
	}
}