package com.meti;

public interface List<T> {
	List<T> add(T element) throws IndexException, CollectionException;

	List<T> set(int index, T element) throws IndexException, CollectionException;

	Stream<T> stream();
}
