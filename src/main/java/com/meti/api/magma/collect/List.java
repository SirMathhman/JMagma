package com.meti.api.magma.collect;

public interface List<T> {
	List<T> add(T element);

	T apply(int index) throws IndexException;

	int size();
}
