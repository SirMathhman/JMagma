package com.meti.api.core;

import com.meti.api.extern.Action0;
import com.meti.api.extern.Action1;
import com.meti.api.extern.Function0;
import com.meti.api.extern.Function1;

public class None<T> implements Option<T> {
	private None() {
	}

	public static <T> None<T> None() {
		return new None<T>();
	}

	@Override
	public void ifPresentOrElse(Action1<T> action, Action0 otherwise) {
		otherwise.run();
	}

	@Override
	public <R> Option<R> map(Function1<T, R> mapper) {
		return None();
	}

	@Override
	public Option<T> filter(Function1<T, Boolean> predicate) {
		return None.None();
	}

	@Override
	public <E extends Exception> T orElseThrow(Function0<E> supplier) throws E{
		throw supplier.get();
	}
}
