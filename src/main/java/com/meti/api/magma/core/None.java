package com.meti.api.magma.core;

public class None<T> implements Option<T> {
	@Override
	public boolean isPresent() {
		return false;
	}

	@Override
	public <R> Option<R> map(F1<T, R> mapper) {
		return new None<>();
	}

	@Override
	public T orElse(T other) {
		return other;
	}

	@Override
	public T orElseGet(Supplier<T> supplier) {
		return supplier.get();
	}
}
