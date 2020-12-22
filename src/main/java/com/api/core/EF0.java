package com.api.core;

public interface EF0<T, E extends Exception> {
	void apply(T value) throws E;
}
