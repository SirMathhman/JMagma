package com.meti.api.core;

import com.meti.api.extern.ExceptionalFunction1;
import com.meti.api.extern.Function0;
import com.meti.api.extern.Function1;

public interface Option<T> {
	<R> Option<R> map(Function1<T, R> mapper);

	<R, E extends Exception> Option<R> mapExceptionally(ExceptionalFunction1<T, R, E> mapper) throws E;

	T get();

	T orElse(T other);

	Option<T> filter(Function1<T, Boolean> predicate);

	boolean isPresent();

	<E extends Exception> T orElseThrow(Function0<E> supplier) throws E;

	boolean isEmpty();
}
