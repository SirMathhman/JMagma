package com.meti.compile.content;

import com.meti.api.java.collect.stream.JavaStream;
import com.meti.api.java.collect.stream.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class JavaState implements State {
	private final List<String> lines;
	private final String buffer;
	private final int depth;

	JavaState() {
		this(new ArrayList<>(), "", 0);
	}

	JavaState(List<String> lines, String buffer, int depth) {
		this.lines = lines;
		this.buffer = buffer;
		this.depth = depth;
	}

	@Override
	public State advance() {
		var copy = new ArrayList<>(lines);
		copy.add(buffer);
		return new JavaState(copy, "", depth);
	}

	@Override
	public State append(char c) {
		return new JavaState(lines, buffer + c, depth);
	}

	@Override
	public boolean isLevel() {
		return depth == 0;
	}

	@Override
	public boolean isShallow() {
		return depth == 1;
	}

	@Override
	public State reset() {
		return new JavaState(lines, buffer, 0);
	}

	@Override
	public State sink() {
		return new JavaState(lines, buffer, depth + 1);
	}

	@Override
	public Stream<String> stream() {
		return new JavaStream<>(lines.stream());
	}

	@Override
	public State surface() {
		return new JavaState(lines, buffer, depth - 1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lines, buffer, depth);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		JavaState state = (JavaState) o;
		var sameDepth = depth == state.depth;
		var sameLines = Objects.equals(lines, state.lines);
		var sameBuffer = Objects.equals(buffer, state.buffer);
		return sameDepth && sameLines && sameBuffer;
	}

	@Override
	public String toString() {
		var linesToString = lines.stream()
				.map("\"%s\""::formatted)
				.collect(Collectors.joining(",", "[", "]"));
		return """
				{"lines" : %s, "buffer" : "%s", depth : %d}""".formatted(linesToString, buffer, depth);
	}

}
