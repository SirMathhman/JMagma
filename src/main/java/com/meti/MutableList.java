package com.meti;

import com.meti.api.collect.IndexException;
import com.meti.api.collect.List;

public interface MutableList<T> extends List<T> {
	MutableList<T> set(int index, T value) throws IndexException;

	MutableList<T> add(T t);

	MutableList<T> addAll(List<T> others);

	<R> MutableList<R> empty();
}
