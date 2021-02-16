package com.meti;

public interface List<T> {
	List<T> add(T element);

	Stream<T> stream();
}
