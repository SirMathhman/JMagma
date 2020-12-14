package com.meti.api.collect.stream;

import com.meti.api.core.Option;
import com.meti.api.extern.ExceptionFunction1;
import com.meti.api.extern.ExceptionFunction2;
import com.meti.api.extern.Function1;
import com.meti.api.extern.Function2;

import static com.meti.api.core.None.None;

public class EmptyStream<T> implements Stream<T> {
	private EmptyStream() {
	}

	public static <T> EmptyStream<T> EmptyStream() {
		return new EmptyStream<T>();
	}

	@Override
	public Option<T> head() throws StreamException {
		return None();
	}

	@Override
	public boolean anyMatch(Function1<T, Boolean> predicate) throws StreamException {
		return false;
	}

	@Override
	public Stream<T> filter(Function1<T, Boolean> predicate) {
		return EmptyStream();
	}

	@Override
	public <R> Stream<R> map(Function1<T, R> mapper) {
		return EmptyStream();
	}

	@Override
	public <R, E extends Exception> Stream<R> mapExceptionally(ExceptionFunction1<T, R, E> mapper) throws StreamException {
		return null;
	}

	@Override
	public <R> R foldLeft(R identity, Function2<R, T, R> mapper) {
		return identity;
	}

	@Override
	public <R> R foldLeftExceptionally(R identity, ExceptionFunction2<R, T, R, ?> mapper) throws StreamException {
		return identity;
	}
}
