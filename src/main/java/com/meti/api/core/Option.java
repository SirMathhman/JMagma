package com.meti.api.core;

public interface Option<T> {
	<R> Option<R> map(F1<T, R> mapper);

	void ifPresent(Consumer<T> consumer);

	T orElse(T other);

	<R, E extends Exception> Option<R> mapExceptionally(EF1<T, R, E> mapper) throws E;

	<E extends Exception> T orElseThrow(Supplier<E> supplier) throws E;

	boolean isEmpty();

	boolean isPresent();

	<R> Option<R> flatMap(F1<T, Option<R>> mapper);

	Option<T> filter(F1<T, Boolean> predicate);

	T orElseGet(Supplier<T> supplier);
}
