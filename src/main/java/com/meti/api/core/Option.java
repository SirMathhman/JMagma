package com.meti.api.core;

import com.meti.api.extern.Action0;
import com.meti.api.extern.Action1;
import com.meti.api.extern.Function0;
import com.meti.api.extern.Function1;

public interface Option<T> {
	void ifPresentOrElse(Action1<T> action, Action0 otherwise);

	<R> Option<R> map(Function1<T, R> mapper);

	Option<T> filter(Function1<T, Boolean> predicate);

	<E extends Exception> T orElseThrow(Function0<E> supplier) throws E;

	T orElse(T other);
}
