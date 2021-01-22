package com.meti.compile.io;

import com.meti.api.magma.collect.Stream;
import com.meti.api.magma.collect.StreamException;
import com.meti.api.magma.collect.Streams;

import java.util.List;
import java.util.stream.Collectors;

public record ListSource(List<String> items) implements Source {
	@Override
	public String apply(int index) {
		return items.get(index);
	}

	@Override
	public String createName() {
		return apply(size() - 1);
	}

	@Override
	public int size() {
		return items.size();
	}

	@Override
	public Stream<String> streamParent() throws StreamException {
		return Streams.ofIntRange(0, size() - 1)
				.map(this::apply);
	}

	@Override
	public String toString() {
		return items.stream()
				.map("\"%s\""::formatted)
				.collect(Collectors.joining(",", "[", "]"));
	}
}
