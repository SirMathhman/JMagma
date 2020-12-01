package com.meti.api.collect.stream;

import com.meti.api.core.Option;
import com.meti.api.extern.ExceptionalFunction1;
import com.meti.api.extern.ExceptionalFunction2;
import com.meti.api.extern.Function1;
import com.meti.api.extern.Function2;

public abstract class AbstractStream<T> implements Stream<T> {
	protected abstract Option<T> next() throws StreamException;

	@Override
	public boolean anyMatch(Function1<T, Boolean> predicate) throws StreamException {
		var option = next();
		while (option.isPresent()) {
			var value = option.orElseThrow(() -> new StreamException("Option was marked as present but not returned."));
			if (predicate.apply(value)) return true;
		}
		return false;
	}

	@Override
	public Stream<T> filter(Function1<T, Boolean> predicate) {
		return new SuppliedStream<>(() -> {
			var option = next();
			while (option.isPresent()) {
				if (predicate.apply(option.get())) {
					return option;
				}
			}
			return option;
		});
	}

	@Override
	public <R> Stream<R> map(Function1<T, R> mapper) throws StreamException {
		return mapExceptionally(mapper::apply);
	}

	@Override
	public <R> Stream<R> mapExceptionally(ExceptionalFunction1<T, R, ?> mapper) throws StreamException {
		return new SuppliedStream<>(() -> {
			try {
				return next().mapExceptionally(mapper);
			} catch (Exception e) {
				throw new StreamException(e);
			}
		});
	}

	@Override
	public <R, E extends Exception> R foldExceptionally(R identity, ExceptionalFunction2<R, T, R, E> mapper) throws StreamException, E {
		var current = identity;
		var next = next();
		while (next.isPresent()) {
			current = mapper.apply(current, next.get());
		}
		return current;
	}

	@Override
	public <R> R fold(R identity, Function2<R, T, R> mapper) throws StreamException {
		return foldExceptionally(identity, (ExceptionalFunction2<R, T, R, StreamException>) mapper::apply);
	}

	@Override
	public Option<T> head() throws StreamException {
		return next();
	}
}
