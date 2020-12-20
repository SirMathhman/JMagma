package com.meti.api.core;

public interface Option<T> {
	<R> Option<R> map(F1<T, R> mapper);

	<E extends Exception> void ifPresentExceptionally(EF0<T, E> consumer) throws E;

	void ifPresent(F0<T> consumer);

	T orElse(T other);

	<R, E extends Exception> Option<R> mapExceptionally(EF1<T, R, E> mapper) throws E;
}
