package com.meti.api.collect.stream;

import com.meti.api.core.Option;
import com.meti.api.extern.ExceptionFunction1;
import com.meti.api.extern.ExceptionFunction2;
import com.meti.api.extern.Function1;
import com.meti.api.extern.Function2;

import static com.meti.api.collect.stream.EmptyStream.EmptyStream;
import static com.meti.api.collect.stream.StreamException.StreamException;
import static com.meti.api.core.Some.Some;

public class SingletonStream<T> implements Stream<T> {
	private final T value;

	private SingletonStream(T value) {
		this.value = value;
	}

	public static <T> SingletonStream<T> SingletonStream(T value) {
		return new SingletonStream<T>(value);
	}

	@Override
	public Option<T> head() throws StreamException {
		return Some(value);
	}

	@Override
	public boolean anyMatch(Function1<T, Boolean> predicate) throws StreamException {
		return predicate.apply(value);
	}

	@Override
	public Stream<T> filter(Function1<T, Boolean> predicate) {
		return predicate.apply(value) ? SingletonStream(value) : EmptyStream();
	}

	@Override
	public <R> Stream<R> map(Function1<T, R> mapper) {
		return SingletonStream(mapper.apply(value));
	}

	@Override
	public <R, E extends Exception> Stream<R> mapExceptionally(ExceptionFunction1<T, R, E> mapper) throws StreamException {
		return null;
	}

	@Override
	public <R> R foldLeft(R identity, Function2<R, T, R> mapper) {
		return mapper.apply(identity, value);
	}

	@Override
	public <R> R foldLeftExceptionally(R identity, ExceptionFunction2<R, T, R, ?> mapper) throws StreamException {
		try {
			return mapper.apply(identity, value);
		} catch (Exception e) {
			throw StreamException(e);
		}
	}
}
