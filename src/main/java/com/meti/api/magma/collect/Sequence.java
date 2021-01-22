package com.meti.api.magma.collect;

public interface Sequence<T> {
	T apply(int index) throws IndexException;

	int size();
}
