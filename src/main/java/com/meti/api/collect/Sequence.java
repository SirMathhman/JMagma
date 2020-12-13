package com.meti.api.collect;

import com.meti.api.core.Option;
import com.meti.api.extern.Function1;

public interface Sequence<T> extends Comparable<Sequence<T>>, Container<T> {
	T apply(int index) throws IndexException;

	Option<Integer> firstOptionally(T t);

	Option<Integer> firstOptionally(Function1<T, Boolean> predicate);

	@Deprecated
	int first(T t);

	Object[] asArray();

	@Deprecated
	int first(Function1<T, Boolean> predicate);
}
