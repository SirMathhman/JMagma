package com.meti.api.java.collect.stream;

import com.meti.api.java.core.JavaOption;
import com.meti.api.magma.core.F1E1;
import com.meti.api.magma.core.F2E1;
import com.meti.api.magma.core.Option;

public interface Stream<T> {
	Stream<T> filter(F1E1<T, Boolean, ?> predicate);

	<R> R fold(R identity, F2E1<R, T, R, ?> folder);

	Option<T> fold(F2E1<T, T, T, ?> folder);

	<R> Stream<R> map(F1E1<T, R, ?> mapper);
}
