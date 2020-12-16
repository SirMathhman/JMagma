package com.meti.api.core;

import com.meti.api.extern.*;

import static com.meti.api.core.None.None;

public class Some<T> implements Option<T> {
	private final T value;

	private Some(T value) {
		this.value = value;
	}

	public static <T> Some<T> Some(T value) {
		return new Some<>(value);
	}

	@Override
	public boolean ifPresentOrElse(Action1<T> action, Action0 otherwise) {
		action.accept(value);
		return true;
	}

	@Override
	public <R> Option<R> map(Function1<T, R> mapper) {
		return Some(mapper.apply(value));
	}

	@Override
	public <R, E extends Exception> Option<R> mapExceptionally(ExceptionFunction1<T, R, E> mapper) throws E {
		return Some(mapper.apply(value));
	}

	@Override
	public Option<T> filter(Function1<T, Boolean> predicate) {
		return predicate.apply(value) ? this : None();
	}

	@Override
	public <E extends Exception> T orElseThrow(Function0<E> supplier) throws E {
		return value;
	}

	@Override
	public T orElse(T other) {
		return value;
	}

	@Override
	public boolean isPresent() {
		return true;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public <E extends Exception> T orElseGetExceptionally(ExceptionFunction0<T, E> supplier) throws E {
		return value;
	}

	@Override
	public <E extends Exception> Option<T> ensure(ExceptionFunction0<T, E> supplier) throws E {
		return this;
	}

	@Override
	public T orElseGet(Function0<T> supplier) {
		return value;
	}

	@Override
	public <R> Option<R> flatMap(Function1<T, Option<R>> mapper) {
		return mapper.apply(value);
	}

	@Override
	public boolean equalsTo(Option<T> other) {
		return other.isPresent();
	}
}
