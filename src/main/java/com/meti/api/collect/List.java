package com.meti.api.collect;

public interface List<T> extends Collection {
	T get(int index) throws IndexException;
}

