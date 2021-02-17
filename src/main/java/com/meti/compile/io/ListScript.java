package com.meti.compile.io;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ListScript implements Script {
	private final List<String> names;
	private final List<Script> children;

	public ListScript(List<String> names) {
		this(names, new ArrayList<>());
	}

	public ListScript(List<String> names, List<Script> children) {
		this.names = names;
		this.children = children;
	}

	@Override
	public Stream<String> stream() {
		return names.stream();
	}

	@Override
	public Stream<Script> streamAll() {
		var first = children.stream().flatMap(Script::streamAll);
		var second = children.stream();
		return Stream.concat(Stream.concat(first, second), Stream.of(this));
	}
}
