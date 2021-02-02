package com.meti.api.magma.core;

public interface Option<T> {
	boolean isPresent();

	<R> Option<R> map(F1<T, R> mapper);

	T orElse(T other);

	T orElseGet(Supplier<T> supplier);
}
