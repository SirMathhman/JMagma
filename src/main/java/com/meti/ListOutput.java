package com.meti;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListOutput implements Output {
	private final List<Output> children = new ArrayList<>();

	private ListOutput() {
	}

	public static ListOutput ListOutput() {
		return new ListOutput();
	}

	@Override
	public Output appendChar(char c) {
		children.add(new CharOutput(c));
		return this;
	}

	@Override
	public Output appendOutput(Output output) {
		children.add(output);
		return this;
	}

	@Override
	public Output appendString(String s) {
		children.add(new StringOutput(s));
		return this;
	}

	@Override
	public String compute() throws RenderException {
		var buffer = new StringBuilder();
		for (Output child : children) {
			buffer.append(child.compute());
		}
		return buffer.toString();
	}

	@Override
	public Output prependChar(char c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Output prependString(String s) {
		children.add(0, new StringOutput(s));
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(children);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ListOutput that = (ListOutput) o;
		return Objects.equals(children, that.children);
	}

	@Override
	public String toString() {
		var childrenString = children.stream()
				.map(Output::toString)
				.collect(Collectors.joining(",", "[", "]"));
		return "{\"children\":%s}".formatted(childrenString);
	}
}
