package com.meti.api.collect.stream;

import com.meti.api.core.Option;
import com.meti.api.extern.ExceptionFunction2;
import com.meti.api.extern.Function1;

public interface Stream<T> {
	Option<T> head() throws StreamException;

	boolean anyMatch(Function1<T, Boolean> predicate) throws StreamException;

	Stream<T> filter(Function1<T, Boolean> predicate);

	<R> Stream<R> map(Function1<T, R> mapper);

	<R> R foldLeft(R identity, ExceptionFunction2<R, T, R, ?> mapper) throws StreamException;
}
