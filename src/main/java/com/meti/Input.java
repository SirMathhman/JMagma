package com.meti;

import java.util.Objects;

public class Input {
	private final String content;

	public Input(String content) {
		this.content = content;
	}

	public int firstChar(char c) {
		return getContent().indexOf(c);
	}

	public String getContent() {
		return content;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Input input = (Input) o;
		return Objects.equals(content, input.content);
	}

	@Override
	public String toString() {
		return "{\"content\":\"%s\"}".formatted(content);
	}

	public Input slice(int from) {
		return slice(from, content.length());
	}

	Input slice(int from, int to) {
		var typeString = getContent().substring(from, to).trim();
		return new Input(typeString);
	}
}
