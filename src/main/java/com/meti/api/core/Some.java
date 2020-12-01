package com.meti.api.core;

import com.meti.api.extern.ExceptionalFunction1;
import com.meti.api.extern.Function0;
import com.meti.api.extern.Function1;

import static com.meti.api.core.None.None;

public class Some<T> implements Option<T> {
	private final T value;

	private Some(T value) {
		this.value = value;
	}

	public static <T> Some<T> Some(T value) {
		return new Some<>(value);
	}

	@Override
	public <R> Option<R> map(Function1<T, R> mapper) {
		return new Some<>(mapper.apply(value));
	}

	@Override
	public <R, E extends Exception> Option<R> mapExceptionally(ExceptionalFunction1<T, R, E> mapper) throws E {
		return new Some<>(mapper.apply(value));
	}

	@Override
	public T get() {
		return value;
	}

	@Override
	public T orElse(T other) {
		return value;
	}

	@Override
	public Option<T> filter(Function1<T, Boolean> predicate) {
		return predicate.apply(value) ? this : None();
	}

	@Override
	public boolean isPresent() {
		return true;
	}

	@Override
	public <E extends Exception> T orElseThrow(Function0<E> supplier) throws E {
		return value;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
