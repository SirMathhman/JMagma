package com.meti.api.magma.core;

import com.meti.api.magma.except.Exception;

public class None<T> implements Option<T> {
	private None() {
	}

	public static <T> Option<T> None() {
		return new None<>();
	}

	@Override
	public <E extends Exception> void ifPresent(C1E1<T, E> actor) throws E {
	}

	@Override
	public <R> Option<R> map(F1<T, R> mapper) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T orElseGet(Supplier<T> supplier) {
		throw new UnsupportedOperationException();
	}
}
