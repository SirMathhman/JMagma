package com.meti.api.magma.collect;

public interface List<T> extends Sequence<T> {
	List<T> add(T element) throws CollectionException;

	List<T> set(int index, T element) throws IndexException;
}
