package com.meti.api.java.collect.list;

import com.meti.api.magma.collect.stream.Stream;

public interface List<T> {
	List<T> add(T buffer);

	boolean contains(T o);

	Stream<T> stream();
}
