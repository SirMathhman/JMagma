package com.meti.compile.content;

import com.meti.api.java.collect.stream.JavaStream;
import com.meti.api.java.collect.stream.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BracketSplitter implements Splitter {
	public static final Splitter BracketSplitter_ = new BracketSplitter();

	BracketSplitter() {
	}

	@Override
	public Stream<String> stream(String content) {
		return processAll(content)
				.advance()
				.stream()
				.filter(s -> !s.isBlank())
				.map(String::trim);
	}

	State processAll(String content) {
		var current = new State();
		for (int i = 0; i < content.length(); i++) {
			current = process(current, content.charAt(i));
		}
		return current;
	}

	State process(State state, char c) {
		if (c == '}' && state.depth == 1) {
			return state.reset().append('}').advance();
		} else if (c == ';' && state.depth == 0) {
			return state.advance();
		} else if (c == '{') {
			return state.sink().append(c);
		} else if (c == '}') {
			return state.surface().append(c);
		} else {
			return state.append(c);
		}
	}

	static class State {
		private final List<String> lines;
		private final String buffer;
		private final int depth;

		State() {
			this(new ArrayList<>(), "", 0);
		}

		State(List<String> lines, String buffer, int depth) {
			this.lines = lines;
			this.buffer = buffer;
			this.depth = depth;
		}

		private State advance() {
			var copy = new ArrayList<>(lines);
			copy.add(buffer);
			return new State(copy, "", depth);
		}

		private State append(char c) {
			return new State(lines, buffer + c, depth);
		}

		@Override
		public int hashCode() {
			return Objects.hash(lines, buffer, depth);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			State state = (State) o;
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

		private State reset() {
			return new State(lines, buffer, 0);
		}

		private State sink() {
			return new State(lines, buffer, depth + 1);
		}

		private Stream<String> stream() {
			return new JavaStream<>(lines.stream());
		}

		private State surface() {
			return new State(lines, buffer, depth - 1);
		}
	}
}