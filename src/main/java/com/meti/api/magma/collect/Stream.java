package com.meti.api.magma.collect;

import com.meti.api.magma.core.F1;
import com.meti.api.magma.core.F1E1;
import com.meti.api.magma.core.F2E1;

public interface Stream<T> {
	Stream<T> filter(F1<T, Boolean> predicate);

	<R> R fold(R identity, F2E1<R, T, R, ?> folder) throws StreamException;

	T head() throws StreamException;

	<R> Stream<R> map(F1E1<T, R, ?> mapper) throws StreamException;
}
