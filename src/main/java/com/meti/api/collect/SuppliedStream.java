package com.meti.api.collect;

import com.meti.api.extern.ExceptionFunction2;
import com.meti.api.extern.Function1;

import static com.meti.api.collect.StreamException.StreamException;

public abstract class SuppliedStream<T> implements Stream<T> {
	@Override
	public <R> Stream<R> map(Function1<T, R> mapper) {
		return new DelegatedStream<>(() -> mapper.apply(get()));
	}

	protected abstract T get() throws StreamException;

	@Override
	public <R> R foldLeft(R identity, ExceptionFunction2<R, T, R, ?> mapper) throws StreamException {
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
}
