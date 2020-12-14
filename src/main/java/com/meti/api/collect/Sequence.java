package com.meti.api.collect;

import com.meti.api.core.Comparable;

public interface Sequence<T> extends Comparable<Sequence<T>>, Container<T> {
	T apply(int index) throws IndexException;

	Object[] asArray();
}
