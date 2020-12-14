package com.meti.api.core;

public interface Comparator<T> extends Equator<T> {
	int compareTo(T first, T second);

	default boolean equalsTo(T first, T second) {
		return compareTo(first, second) == 0;
	}
}
