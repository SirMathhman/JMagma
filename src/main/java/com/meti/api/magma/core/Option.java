package com.meti.api.magma.core;

public interface Option<T> {
	boolean isPresent();

	T orElse(T other);
}
