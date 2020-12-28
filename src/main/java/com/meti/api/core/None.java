package com.meti.api.core;

public class None<T> implements Option<T> {
	private None() {
	}

	public static <T> None<T> None() {
		return new None<>();
	}

	@Override
	public <R> Option<R> map(F1<T, R> mapper) {
		return None();
	}

	@Override
	public void ifPresent(Consumer<T> consumer) {
	}

	@Override
	public T orElse(T other) {
		return other;
	}

	@Override
	public <R, E extends Exception> Option<R> mapExceptionally(EF1<T, R, E> mapper) throws E {
		return None();
	}

	@Override
	public <E extends Exception> T orElseThrow(Supplier<E> supplier) throws E {
		throw supplier.apply();
	}

	@Override
	public boolean isEmpty() {
		return true;
	}
}
