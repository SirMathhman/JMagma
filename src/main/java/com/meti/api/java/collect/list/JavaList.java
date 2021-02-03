package com.meti.api.java.collect.list;

import com.meti.api.java.collect.JavaLists;
import com.meti.api.magma.collect.stream.Stream;

import java.util.ArrayList;
import java.util.List;

public class JavaList<T> implements com.meti.api.java.collect.list.List<T> {
	private final List<T> lines;

	public JavaList(List<T> lines) {
		this.lines = lines;
	}

	@Override
	public com.meti.api.java.collect.list.List<T> add(T buffer) {
		var copy = new ArrayList<>(lines);
		copy.add(buffer);
		return new JavaList<>(copy);
	}

	@Override
	public boolean contains(T o) {
		return lines.contains(o);
	}

	@Override
	public Stream<T> stream() {
		return JavaLists.stream(lines);
	}
}
