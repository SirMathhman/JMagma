package com.meti.api.collect.stream;

import com.meti.api.core.Option;
import com.meti.api.extern.ExceptionFunction1;
import com.meti.api.extern.ExceptionFunction2;
import com.meti.api.extern.Function1;
import com.meti.api.extern.Function2;

public interface Stream<T> {
	Option<T> head() throws StreamException;

	boolean anyMatch(Function1<T, Boolean> predicate) throws StreamException;

	Stream<T> filter(Function1<T, Boolean> predicate);

	<R> Stream<R> map(Function1<T, R> mapper);
/*
	<R> Stream<R> flatMap(Function1<T, Stream<R>> mapper);*/

	<R, E extends Exception> Stream<R> mapExceptionally(ExceptionFunction1<T, R, E> mapper) throws StreamException;

	<R> R foldLeft(R identity, Function2<R, T, R> mapper);

	<R> R foldLeftExceptionally(R identity, ExceptionFunction2<R, T, R, ?> mapper) throws StreamException;

	boolean allMatch(Function1<T, Boolean> predicate) throws StreamException;
}
