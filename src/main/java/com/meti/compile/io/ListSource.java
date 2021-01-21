package com.meti.compile.io;

import java.util.List;
import java.util.stream.Collectors;

public record ListSource(List<String> items) implements Source {
	@Override
	public String apply(int index) {
		return items.get(index);
	}

	@Override
	public int size() {
		return items.size();
	}

	@Override
	public String toString() {
		return items.stream()
				.map("\"%s\""::formatted)
				.collect(Collectors.joining(",", "[", "]"));
	}
}
