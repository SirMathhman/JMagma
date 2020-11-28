package com.meti;

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
		return null;
	}

	@Override
	public <R, E extends Exception> Option<R> mapExceptionally(ExceptionalFunction1<T, R, E> mapper) throws E {
		return null;
	}

	@Override
	public T orElse(T other) {
		return value;
	}

	@Override
	public Option<T> filter(Function1<T, Boolean> predicate) {
		return null;
	}

	@Override
	public boolean isPresent() {
		return false;
	}

	@Override
	public <E extends Exception> T orElseThrow(Function0<E> supplier) throws E {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
