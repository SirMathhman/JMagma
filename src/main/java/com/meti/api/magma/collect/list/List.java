package com.meti.api.magma.collect.list;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.collect.stream.Stream;

public interface List<T> {
	List<T> add(T buffer);

	T apply(int index) throws IndexException;

	List<T> set(int index, T element) throws IndexException;

	int size();

	Stream<T> stream();
}
