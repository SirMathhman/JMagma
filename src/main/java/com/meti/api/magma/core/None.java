package com.meti.api.magma.core;

import com.meti.api.magma.except.Exception;

public class None<T> implements Option<T> {
	private None() {
	}

	public static <T> Option<T> None() {
		return new None<>();
	}

	@Override
	public Option<T> filter(F1<T, Boolean> predicate) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <E extends Exception> void ifPresent(C1E1<T, E> actor) throws E {
	}

	@Override
	public <R> Option<R> map(F1<T, R> mapper) {
		return new None<>();
	}

	@Override
	public <R, E extends Exception> Option<R> mapE1(F1E1<T, R, E> mapper) throws E {
		throw new UnsupportedOperationException();
	}

	@Override
	public T orElseGet(Supplier<T> supplier) {
		return supplier.get();
	}
}
