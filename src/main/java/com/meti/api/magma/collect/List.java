package com.meti.api.magma.collect;

public interface List<T> {
	T apply(int index) throws IndexException;

	int size();
}
