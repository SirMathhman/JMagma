package com.meti;

public interface Sequence<T, C> {
	<R> List<R> empty();

	int size();

	T get(int index) throws IndexException;

	boolean isEmpty();
}

