package com.meti;

public interface List<T> extends Sequence<T, List<T>> {
	List<T> add(T t);
}
