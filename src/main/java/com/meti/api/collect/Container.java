package com.meti.api.collect;

public interface Container<T> {
	int size();

	boolean contains(T t);

	boolean containsAll(Container<T> other);
}
