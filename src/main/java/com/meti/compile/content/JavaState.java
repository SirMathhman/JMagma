package com.meti.compile.content;

import com.meti.api.java.collect.stream.JavaStream;
import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.StreamException;
import com.meti.compile.token.Input;

import java.util.ArrayList;
import java.util.List;

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
		return new JavaState(lines, buffer, 0);
	}

	@Override
	public State sink() {
		return new JavaState(lines, buffer, depth + 1);
	}

	@Override
	public Stream<Input> stream(){
		return stream2().map(Input::new);
	}

	private Stream<String> stream2() {
		return new JavaStream<>(lines.stream());
	}

	@Override
	public State surface() {
		return new JavaState(lines, buffer, depth - 1);
	}

	@Override
	public boolean equalsTo(State state) {
		try {
			var sameDepth = state.isAt(depth);
			var sameBuffer = state.isStoring(buffer);
			var sameLines = state.stream().map(Input::getContent).allMatch(lines::contains);
			return sameDepth && sameBuffer && sameLines;
		} catch (StreamException e) {
			return false;
		}
	}
}
