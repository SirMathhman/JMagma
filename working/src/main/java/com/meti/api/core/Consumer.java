package com.meti.api.core;

public interface Consumer<T> {
	void apply(T t);
}
