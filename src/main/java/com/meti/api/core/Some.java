package com.meti.api.core;

public class Some<T> implements Option<T> {
	private final T value;

	private Some(T value) {
		this.value = value;
	}

	public static <T> Some<T> Some(T value) {
		return new Some<>(value);
	}

	@Override
	public <R> Option<R> map(F1<T, R> mapper) {
		return Some(mapper.apply(value));
	}

	@Override
	public <E extends Exception> void ifPresentExceptionally(EF0<T, E> consumer) throws E {
		consumer.apply(value);
	}

	@Override
	public void ifPresent(F0<T> consumer) {
		consumer.apply(value);
	}

	@Override
	public T orElse(T other) {
		return value;
	}
}
