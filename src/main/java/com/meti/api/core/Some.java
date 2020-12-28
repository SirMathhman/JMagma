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
	public void ifPresent(Consumer<T> consumer) {
		consumer.apply(value);
	}

	@Override
	public T orElse(T other) {
		return value;
	}

	@Override
	public <R, E extends Exception> Option<R> mapExceptionally(EF1<T, R, E> mapper) throws E {
		return Some(mapper.apply(value));
	}

	@Override
	public <E extends Exception> T orElseThrow(Supplier<E> supplier) throws E {
		return value;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isPresent() {
		return true;
	}
}
