package com.meti.api.magma.collect.stream;

import com.meti.api.magma.core.F1E1;
import com.meti.api.magma.core.F2E1;
import com.meti.api.magma.core.Option;

public interface Stream<T> {
	boolean allMatch(F1E1<T, Boolean, ?> predicate) throws StreamException;

	Stream<T> filter(F1E1<T, Boolean, ?> predicate);

	<R> R fold(R identity, F2E1<R, T, R, ?> folder) throws StreamException;

	Option<T> fold(F2E1<T, T, T, ?> folder) throws StreamException;

	<R> Stream<R> map(F1E1<T, R, ?> mapper);
}
