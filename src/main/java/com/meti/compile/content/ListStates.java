package com.meti.compile.content;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.collect.list.ArrayLists;
import com.meti.api.magma.collect.list.List;
import com.meti.api.magma.collect.list.Lists;
import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Input;

public class ListStates {
	public static final State EmptyListState = new ListStates.Impl(new Input(""), ArrayLists.empty(), 0, 0, 0);

	public ListStates() {
	}

	public static Builder ListState() {
		return new Builder();
	}

	public static class Builder {
		private final Input input;
		private final List<Input> children;
		private final int depth;
		private final int from;
		private final int to;

		private Builder() {
			this(new Input(""), ArrayLists.empty(), 0, 0, 0);
		}

		public Builder(Input input, List<Input> children, int depth, int from, int to) {
			this.input = input;
			this.children = children;
			this.depth = depth;
			this.from = from;
			this.to = to;
		}

		public Builder append(Input child) {
			return new Builder(input, children.add(child), depth, from, to);
		}

		public Builder at(int depth) {
			return new Builder(input, children, depth, from, to);
		}

		public State complete() {
			return new Impl(input, children, depth, from, to);
		}

		public Builder from(int from) {
			return new Builder(input, children, depth, from, to);
		}

		public Builder to(int to) {
			return new Builder(input, children, depth, from, to);
		}

		public Builder of(Input input) {
			return new Builder(input, children, depth, from, to);
		}
	}

	private static class Impl implements State {
		private final List<Input> children;
		private final int depth;
		private final Input input;
		private final int from;
		private final int to;

		Impl(Input input, List<Input> list, int depth, int from, int to) {
			this.input = input;
			this.children = list;
			this.depth = depth;
			this.from = from;
			this.to = to;
		}

		@Override
		public State advance() {
			return new Impl(input, children, depth, from, to + 1);
		}

		@Override
		public State complete() {
			try {
				var child = computeBuffer();
				return ListState()
						.of(input)
						.append(child)
						.at(depth)
						.from(to)
						.to(to)
						.complete();
			} catch (IndexException e) {
				return EmptyListState;
			}
		}

		@Override
		public boolean isAt(int depth) {
			return this.depth == depth;
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
		public boolean isStoring(Input input) {
			try {
				var buffer = computeBuffer();
				return buffer.hasSameContent(input);
			} catch (IndexException e) {
				return false;
			}
		}

		@Override
		public State sink() {
			return new Impl(input, children, depth + 1, from, to);
		}

		@Override
		public Stream<Input> stream() {
			return children.stream();
		}

		@Override
		public State surface() {
			return new Impl(input, children, depth - 1, from, to);
		}

		private Input computeBuffer() throws IndexException {
			return input.slice(from, to);
		}

		@Override
		public String asString() {
			var inputString = input.asString();
			var childrenString = Lists.asString(children);
			return ("{\"input\" : %s," +
			        "\"children\" : %s," +
			        "\"depth\" : %d," +
			        "\"from\" : %d," +
			        "\"to\": %d}")
					.formatted(inputString, childrenString, depth, from, to);
		}

		@Override
		public boolean equalsTo(State state) {
			try {
				var buffer = computeBuffer();
				var sameDepth = state.isAt(depth);
				var sameBuffer = state.isStoring(buffer);
				var sameLines = state.stream().allMatch(input -> Lists.contains(children, input));
				return sameDepth && sameBuffer && sameLines;
			} catch (StreamException | IndexException e) {
				return false;
			}
		}
	}
}
