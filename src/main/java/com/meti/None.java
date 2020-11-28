package com.meti;

public class None<T> implements Option<T> {
	private None() {
	}

	public static <T> None<T> None() {
		return new None<T>();
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
		return other;
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
