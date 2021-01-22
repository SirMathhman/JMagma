package com.meti.compile.content;

import com.meti.api.java.collect.JavaList;
import com.meti.api.magma.collect.Lists;
import com.meti.api.magma.collect.Stream;

import java.util.ArrayList;

public class JavaSplitterState implements SplitterState {
	private final ArrayList<String> lines;
	private StringBuilder buffer;
	private int depth;

	public JavaSplitterState() {
		this(new ArrayList<>(), new StringBuilder(), 0);
	}

	public JavaSplitterState(ArrayList<String> lines, StringBuilder buffer, int depth) {
		this.lines = lines;
		this.buffer = buffer;
		this.depth = depth;
	}

	@Override
	public SplitterState advance() {
		lines.add(buffer.toString());
		this.buffer = new StringBuilder();
		return this;
	}

	@Override
	public SplitterState append(char c) {
		buffer.append(c);
		return this;
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
		this.depth = 0;
		return this;
	}

	@Override
	public SplitterState sink() {
		this.depth = depth + 1;
		return this;
	}

	@Override
	public Stream<String> stream() {
		return Lists.stream(new JavaList<>(lines));
	}

	@Override
	public SplitterState surface() {
		this.depth = depth - 1;
		return this;
	}
}
