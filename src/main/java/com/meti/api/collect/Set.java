package com.meti.api.collect;

import com.meti.api.extern.Function1;

public interface Set<T, C extends Set<T, C>> extends Container<T> {
	boolean intersects(Container<T> other);

	C removeAll(Function1<T, Boolean> predicate);

	C remove(int index) throws IndexException;

	C removeFirst(T t);

	C add(T t);
}
