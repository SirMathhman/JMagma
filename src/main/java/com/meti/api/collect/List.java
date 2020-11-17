package com.meti.api.collect;

public interface List<T> {
	Object[] toArray();

	List<T> set(int index, T value) throws IndexException;

	List<T> add(T value);

	List<T> asEmpty();

	int size();

	T get(int index) throws IndexException;
}
