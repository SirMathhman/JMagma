package com.meti.api.collect.list;

import com.meti.api.collect.IndexException;
import com.meti.api.collect.Sequence;

public interface List<T> extends Sequence<T> {
	@Override
	T apply(int index) throws IndexException;

	boolean contains(T t);

	int indexOf(T t);

	List<T> set(int index, T t);

	List<T> remove(T t);

	List<T> add(T t);
}
