package com.meti.compile.token;

import com.meti.core.F1;
import com.meti.core.F1E1;

import java.util.Objects;
import java.util.stream.Stream;

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

	public <T> T peek(F1<String, T> peeker) {
		return peeker.apply(content);
	}

	public Input slice(int from) {
		return slice(from, content.length());
	}

	Input slice(int from, int to) {
		var typeString = getContent().substring(from, to).trim();
		return new Input(typeString);
	}

	public Stream<Character> stream() {
		var builder = Stream.<Character>builder();
		for (int i = 0; i < content.length(); i++) {
			builder.add(content.charAt(i));
		}
		return builder.build();
	}

	public <E extends Exception> boolean test(F1E1<String, Boolean, E> predicate) throws E {
		return predicate.apply(content);
	}
}
