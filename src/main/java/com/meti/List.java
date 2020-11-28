package com.meti;

public interface List<T> {
	int size();

	T get(int index) throws IndexException;

	boolean isEmpty();
}

