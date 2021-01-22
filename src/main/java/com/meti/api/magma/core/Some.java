package com.meti.api.magma.core;

import com.meti.api.magma.except.Exception;

import static com.meti.api.magma.core.None.None;

public final record Some<T>(T value) implements Option<T> {
	@Override
	public Option<T> filter(F1<T, Boolean> predicate) {
		return predicate.apply(value) ? Some(value) : None();
	}

	public static <T> Option<T> Some(T value) {
		return new Some<>(value);
	}

	@Override
	public <E extends Exception> void ifPresent(C1E1<T, E> actor) throws E {
		actor.accept(value);
	}

	@Override
	public <R> Option<R> map(F1<T, R> mapper) {
		return new Some<>(mapper.apply(value));
	}

	@Override
	public <R, E extends Exception> Option<R> mapE1(F1E1<T, R, E> mapper) throws E {
		return new Some<>(mapper.apply(value));
	}

	@Override
	public T orElseGet(Supplier<T> supplier) {
		return value;
	}

	@Override
	public <E extends Exception> T orElseThrow(Supplier<E> supplier) throws E {
		throw new UnsupportedOperationException();
	}
}
