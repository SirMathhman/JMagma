package com.meti.compile.token;

import com.meti.core.F1;
import com.meti.core.F1E1;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class RootInput implements Input {
	private final String content;

	public RootInput(String content) {
		this.content = content;
	}

	@Override
	public char apply(int i) {
		return content.charAt(i);
	}

	@Override
	public Optional<Integer> firstChar(char c) {
		var index = content.indexOf(c);
		if (index == -1) return Optional.empty();
		return Optional.of(index);
	}

	@Override
	public String format(String format) {
		return format.formatted(content);
	}

	@Override
	public boolean is(String name) {
		return content.equals(name);
	}

	@Override
	public boolean isEmpty() {
		return content.isEmpty();
	}

	@Override
	public Optional<Integer> last(char c) {
		var index = content.lastIndexOf(c);
		if (index == -1) return Optional.empty();
		return Optional.of(index);
	}

	@Override
	public <T> T map(F1<String, T> peeker) {
		return peeker.apply(content);
	}

	@Override
	public int size() {
		return content.length();
	}

	@Override
	public Input slice(int from) {
		return slice(from, content.length());
	}

	@Override
	public Input slice(int from, int to) {
		var typeString = content.substring(from, to).trim();
		return new RootInput(typeString);
	}

	@Override
	public Stream<Character> stream() {
		var builder = Stream.<Character>builder();
		for (int i = 0; i < content.length(); i++) {
			builder.add(content.charAt(i));
		}
		return builder.build();
	}

	@Override
	public <E extends Exception> boolean test(F1E1<String, Boolean, E> predicate) throws E {
		return predicate.apply(content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(content);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RootInput input = (RootInput) o;
		return Objects.equals(content, input.content);
	}

	@Override
	public String toString() {
		return "{\"content\":\"%s\"}".formatted(content);
	}
}
