package com.meti.api.magma.collect;

public class Lists {
	public Lists() {
	}

	public static <T> List<T> addAll(List<T> self, List<T> other) {
		try {
			return Sequences.stream(other).fold(self, List::add);
		} catch (StreamException e) {
			return self;
		}
	}
}
