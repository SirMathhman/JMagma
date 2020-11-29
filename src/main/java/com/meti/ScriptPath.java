package com.meti;

import com.meti.api.collect.ListStream;
import com.meti.api.collect.MutableList;

import java.util.Collection;

public interface ScriptPath<T> {
	ListStream<T> stream();

	int count();

	boolean load(T value);

	MutableList<T> asCollection2();

	@Deprecated
	Collection<T> asCollection();
}
