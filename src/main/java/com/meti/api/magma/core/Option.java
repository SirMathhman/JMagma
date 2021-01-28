package com.meti.api.magma.core;

public interface Option<T> {
	T orElse(T other);
}
