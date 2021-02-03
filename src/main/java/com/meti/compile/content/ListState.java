package com.meti.compile.content;

import com.meti.api.java.collect.list.JavaList;
import com.meti.api.magma.collect.list.List;
import com.meti.api.magma.collect.list.Lists;
import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Input;

import java.util.ArrayList;

class ListState implements State {
	private final List<Input> list;
	private final int depth;
	private final Input input;
	private final int from;
	private final int to;

	ListState(Input input) {
		this(input, new JavaList<>(new ArrayList<>()), 0, 0, 0);
	}

	public ListState(Input input, List<Input> list, int depth, int from, int to) {
		this.input = input;
		this.list = list;
		this.depth = depth;
		this.from = from;
		this.to = to;
	}

	public ListState(Input input, int from, int to) {
		this(input, new JavaList<>(new ArrayList<>()), 0, from, to);
	}

	@Override
	public State complete() {
		var child = computeBuffer();
		var withChild = list.add(child);
		return new ListState(input, withChild, depth, to, to);
	}

	@Override
	public State advance() {
		return new ListState(input, list, depth, from, to + 1);
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
	public boolean isStoring(String buffer) {
		return computeBuffer().getContent().equals(buffer);
	}

	@Override
	public State reset() {
		return new ListState(input, list, 0, 0, 0);
	}

	@Override
	public State sink() {
		return new ListState(input, list, depth + 1, 0, 0);
	}

	@Override
	public Stream<Input> stream() {
		return list.stream();
	}

	@Override
	public State surface() {
		return new ListState(input, list, depth - 1, from, to);
	}

	private Input computeBuffer() {
		return input.slice(from, to);
	}

	@Override
	public boolean equalsTo(State state) {
		try {
			var buffer = computeBuffer().getContent();
			var sameDepth = state.isAt(depth);
			var sameBuffer = state.isStoring(buffer);
			var sameLines = state.stream().allMatch(input -> Lists.contains(list, input));
			return sameDepth && sameBuffer && sameLines;
		} catch (StreamException e) {
			return false;
		}
	}
}
