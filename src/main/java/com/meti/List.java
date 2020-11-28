package com.meti;

public interface List<T> extends Sequence<T, List<T>> {
	List<T> set(int index, T value) throws IndexException;

	List<T> add(T t);
}
