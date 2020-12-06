package com.meti.api.core;

import com.meti.api.extern.Action0;
import com.meti.api.extern.Action1;
import com.meti.api.extern.Function0;
import com.meti.api.extern.Function1;

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
		return false;
	}

	@Override
	public <R> Option<R> map(Function1<T, R> mapper) {
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
}
