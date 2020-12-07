package com.meti.api.core;

public interface Comparable<T> {
	int compareTo(T other);

	default boolean equalsTo(T other) {
		return compareTo(other) == 0;
	}
}
