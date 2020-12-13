package com.meti.api.core;

public interface Comparable<T> {
	@Deprecated
	int compareTo(T other);

	int compareTo2(Object other);

	default boolean equalsTo(T other) {
		return compareTo(other) == 0;
	}
}
