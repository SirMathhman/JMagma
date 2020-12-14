package com.meti.api.collect;

import com.meti.api.core.Equatable;

public interface Sequence<T> extends Equatable<Sequence<T>>, Container<T> {
	T apply(int index) throws IndexException;

	Object[] asArray();
}
