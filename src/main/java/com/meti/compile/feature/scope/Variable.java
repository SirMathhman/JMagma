package com.meti.compile.feature.scope;

import com.meti.compile.feature.Node;

import java.util.Objects;
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Variable variable = (Variable) o;
		return Objects.equals(content, variable.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(content);
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
