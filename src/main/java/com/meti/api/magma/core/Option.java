package com.meti.api.magma.core;

import com.meti.api.magma.except.Exception;

public interface Option<T> {
	Option<T> filter(F1<T, Boolean> predicate);

	<E extends Exception> void ifPresent(C1E1<T, E> actor) throws E;

	<R> Option<R> map(F1<T, R> mapper);

	<R, E extends Exception> Option<R> mapE1(F1E1<T, R, E> mapper) throws E;

	T orElseGet(Supplier<T> supplier);

	<E extends Exception> T orElseThrow(Supplier<E> supplier) throws E;
}
