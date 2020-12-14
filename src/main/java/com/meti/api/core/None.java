package com.meti.api.core;

import com.meti.api.extern.*;

import static com.meti.api.core.Some.Some;

public class None<T> implements Option<T> {
	private None() {
	}

	public static <T> None<T> None() {
		return new None<>();
	}

	@Override
	public boolean ifPresentOrElse(Action1<T> action, Action0 otherwise) {
		otherwise.run();
		return false;
	}

	@Override
	public <R> Option<R> map(Function1<T, R> mapper) {
		return None();
	}

	@Override
	public <R, E extends Exception> Option<R> mapExceptionally(ExceptionFunction1<T, R, E> mapper) throws E {
		return None();
	}

	@Override
	public Option<T> filter(Function1<T, Boolean> predicate) {
		return None();
	}

	@Override
	public <E extends Exception> T orElseThrow(Function0<E> supplier) throws E {
		throw supplier.get();
	}

	@Override
	public T orElse(T other) {
		return other;
	}

	@Override
	public boolean isPresent() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public <E extends Exception> T orElseGet(ExceptionFunction0<T, E> supplier) throws E {
		return supplier.get();
	}

	@Override
	public <E extends Exception> Option<T> ensure(ExceptionFunction0<T, E> supplier) throws E {
		return Some(supplier.get());
	}

	@Override
	public T orElseGet(Function0<T> supplier) {
		return supplier.get();
	}

	@Override
	public <R> Option<R> flatMap(Function1<T, Option<R>> mapper) {
		return None();
	}

	@Override
	public boolean equalsTo(Option<T> other) {
		return other.isEmpty();
	}
}
