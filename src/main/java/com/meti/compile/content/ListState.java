package com.meti.compile.content;

import com.meti.api.java.collect.list.JavaList;
import com.meti.api.java.collect.list.List;
import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Input;

import java.util.ArrayList;

class ListState implements State {
	private final List<String> list;
	private final String buffer;
	private final int depth;

	ListState() {
		this(new JavaList<>(new ArrayList<>()), "", 0);
	}

	ListState(List<String> list, String buffer, int depth) {
		this.list = list;
		this.buffer = buffer;
		this.depth = depth;
	}

	@Override
	public State advance() {
		return new ListState(list.add(buffer), "", depth);
	}

	@Override
	public State append(char c) {
		return new ListState(list, buffer + c, depth);
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
		return this.buffer.equals(buffer);
	}

	@Override
	public State reset() {
		return new ListState(list, buffer, 0);
	}

	@Override
	public State sink() {
		return new ListState(list, buffer, depth + 1);
	}

	@Override
	public Stream<Input> stream() {
		return list.stream().map(Input::new);
	}

	@Override
	public State surface() {
		return new ListState(list, buffer, depth - 1);
	}

	@Override
	public boolean equalsTo(State state) {
		try {
			var sameDepth = state.isAt(depth);
			var sameBuffer = state.isStoring(buffer);
			var sameLines = state.stream()
					.map(Input::getContent)
					.allMatch(list::contains);
			return sameDepth && sameBuffer && sameLines;
		} catch (StreamException e) {
			return false;
		}
	}
}
