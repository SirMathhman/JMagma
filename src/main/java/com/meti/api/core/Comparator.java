package com.meti.api.core;

public interface Comparator<T> {
	int compareTo(T first, T second);

	default boolean equals(T first, T second) {
		return compareTo(first, second) == 0;
	}
}
