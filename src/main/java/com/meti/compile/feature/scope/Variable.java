package com.meti.compile.feature.scope;

import com.meti.compile.feature.Node;

import java.util.Optional;

public class Variable implements Node {
	private final String content;

	private Variable(String content) {
		this.content = content;
	}

	public static Variable Variable(String content) {
		return new Variable(content);
	}

	@Override
	public Optional<String> findContent() {
		return Optional.of(content);
	}

	@Override
	public String render() {
		return content;
	}
}
