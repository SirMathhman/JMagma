package com.meti.api.collect.stream;

import com.meti.api.core.Option;
import com.meti.api.extern.*;

public interface Stream<T> {
	boolean anyMatch(Function1<T, Boolean> predicate);

	Stream<T> filter(Function1<T, Boolean> predicate);

	<R> Stream<R> map(Function1<T, R> mapper) throws StreamException;

	<R> Stream<R> mapExceptionally(ExceptionalFunction1<T, R, ?> mapper) throws StreamException;

	<R, E extends Exception> R foldExceptionally(R identity, ExceptionalFunction2<R, T, R, E> mapper) throws StreamException;

	<R> R fold(R identity, Function2<R, T, R> mapper);

	Option<T> head();
}
