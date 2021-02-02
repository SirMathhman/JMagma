package com.meti.api.magma.core;

public final record Some<T>(T value) implements Option<T> {

	public static <T> Option<T> Some(T value) {
		return new Some<T>(value);
	}

	@Override
	public boolean isPresent() {
		return true;
	}

	@Override
	public <R> Option<R> map(F1<T, R> mapper) {
		return new Some<>(mapper.apply(value));
	}

	@Override
	public T orElse(T other) {
		return value;
	}

	@Override
	public T orElseGet(Supplier<T> supplier) {
		return value;
	}
}
