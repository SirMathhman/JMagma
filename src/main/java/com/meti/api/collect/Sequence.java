package com.meti.api.collect;

public interface Sequence<T> extends Comparable<Sequence<T>> {
	T apply(int index) throws IndexException;

	int size();
}
