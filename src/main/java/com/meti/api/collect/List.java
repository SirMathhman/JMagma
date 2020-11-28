package com.meti.api.collect;

import com.meti.api.collect.IndexException;

public interface List<T> {
	int size();

	T get(int index) throws IndexException;

	boolean isEmpty();
}

