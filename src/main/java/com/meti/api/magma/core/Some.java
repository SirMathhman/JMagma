package com.meti.api.magma.core;

import com.meti.api.magma.except.Exception;

public final record Some<T>(T value) implements Option<T> {
	public static <T> Option<T> Some(T value) {
		return new Some<T>(value);
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
	public T orElseGet(Supplier<T> supplier) {
		return value;
	}
}
