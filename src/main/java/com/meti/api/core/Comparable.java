package com.meti.api.core;

public interface Comparable<T> extends Equatable<T> {
	int compareTo(T other);

	@Override
	default boolean equalsTo(T other) {
		return compareTo(other) == 0;
	}
}
