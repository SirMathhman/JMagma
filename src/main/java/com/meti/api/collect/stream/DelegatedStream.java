package com.meti.api.collect.stream;

import com.meti.api.core.Option;
import com.meti.api.extern.ExceptionFunction1;
import com.meti.api.extern.ExceptionFunction2;
import com.meti.api.extern.Function1;
import com.meti.api.extern.Function2;

import static com.meti.api.collect.stream.StreamException.StreamException;
import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public abstract class DelegatedStream<T> implements Stream<T> {
	@Override
	public <R> Stream<R> map(Function1<T, R> mapper) {
		return new SuppliedStream<>(() -> mapper.apply(get()));
	}

	@Override
	public <R> R foldLeft(R identity, Function2<R, T, R> mapper) {
		try {
			return foldLeftExceptionally(identity, mapper::apply);
		} catch (StreamException e) {
			return identity;
		}
	}

	@Override
	public Option<T> head() throws StreamException {
		try {
			return Some(get());
		} catch (EndOfStreamException e) {
			return None();
		}
	}

	@Override
	public boolean anyMatch(Function1<T, Boolean> predicate) throws StreamException {
		while (true) {
			try {
				if (predicate.apply(get())) return true;
			} catch (EndOfStreamException e) {
				break;
			}
		}
		return false;
	}

	@Override
	public Stream<T> filter(Function1<T, Boolean> predicate) {
		return new SuppliedStream<>(() -> {
			T value;
			do {
				value = get();
			} while (!predicate.apply(value));
			return value;
		});
	}

	protected abstract T get() throws StreamException;

	@Override
	public <R> R foldLeftExceptionally(R identity, ExceptionFunction2<R, T, R, ?> mapper) throws StreamException {
		var current = identity;
		while (true) {
			try {
				current = mapper.apply(current, get());
			} catch (EndOfStreamException e) {
				break;
			} catch (Exception e) {
				throw StreamException(e);
			}
		}
		return current;
	}

	@Override
	public <R, E extends Exception> Stream<R> mapExceptionally(ExceptionFunction1<T, R, E> mapper) throws StreamException {
		return new SuppliedStream<>(() -> {
			try {
				return mapper.apply(get());
			} catch (Exception e) {
				throw StreamException(e);
			}
		});
	}
}
