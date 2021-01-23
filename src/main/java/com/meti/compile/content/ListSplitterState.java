package com.meti.compile.content;

import com.meti.api.magma.collect.*;

public class ListSplitterState implements SplitterState {
	private final List<String> lines;
	private final String buffer;
	private final int depth;

	public ListSplitterState() {
		this(ArrayLists.empty(), "", 0);
	}

	private ListSplitterState(List<String> lines, String buffer, int depth) {
		this.lines = lines;
		this.buffer = buffer;
		this.depth = depth;
	}

	@Override
	public SplitterState advance() throws CollectionException {
		return new ListSplitterState(lines.add(buffer), "", depth);
	}

	@Override
	public SplitterState append(char c) {
		return new ListSplitterState(lines, buffer + c, depth);
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
	public SplitterState reset() {
		return new ListSplitterState(lines, buffer, 0);
	}

	@Override
	public SplitterState sink() {
		return new ListSplitterState(lines, buffer, depth + 1);
	}

	@Override
	public Stream<String> stream() {
		return Lists.stream(lines);
	}

	@Override
	public SplitterState surface() {
		return new ListSplitterState(lines, buffer, depth - 1);
	}
}
