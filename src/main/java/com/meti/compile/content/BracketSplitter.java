package com.meti.compile.content;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BracketSplitter implements Splitter {
	public static final Splitter BracketSplitter_ = new BracketSplitter();

	private BracketSplitter() {
	}

	@Override
	public Stream<String> stream(String content) {
		return processAll(content)
				.advance()
				.stream()
				.filter(s -> !s.isBlank())
				.map(String::trim);
	}

	private State processAll(String content) {
		var state = new State();
		var current = state;
		for (int i = 0; i < content.length(); i++) {
			current = process(state, content.charAt(i));
		}
		return current;
	}

	private State process(State state, char c) {
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

	private static class State {
		private final List<String> lines;
		private final StringBuilder buffer;
		private final int depth;

		private State() {
			this(new ArrayList<>(), new StringBuilder(), 0);
		}

		private State(List<String> lines, StringBuilder buffer, int depth) {
			this.lines = lines;
			this.buffer = buffer;
			this.depth = depth;
		}

		private State advance() {
			var copy = new ArrayList<>(lines);
			copy.add(buffer.toString());
			return new State(copy, new StringBuilder(), depth);
		}

		private State append(char c) {
			return new State(lines, buffer.append(c), depth);
		}

		private State reset() {
			return new State(lines, buffer, 0);
		}

		private State sink() {
			return new State(lines, buffer, depth + 1);
		}

		private Stream<String> stream() {
			return lines.stream();
		}

		private State surface() {
			return new State(lines, buffer, depth - 1);
		}
	}
}