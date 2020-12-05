package com.meti.api.collect.stream;

import com.meti.api.extern.ExceptionFunction2;
import com.meti.api.extern.Function1;

public interface Stream<T> {
	<R> Stream<R> map(Function1<T, R> mapper);

	<R> R foldLeft(R identity, ExceptionFunction2<R, T, R, ?> mapper) throws StreamException;
}
