package com.meti.api.collect.list;

import com.meti.api.collect.Sequence;
import com.meti.api.collect.Set;
import com.meti.api.collect.stream.Stream;
import com.meti.api.extern.Function1;

public interface List<T> extends Sequence<T>, Set<T, List<T>> {
	Stream<T> stream();

	List<T> set(int index, T t);

	List<T> clear();

	List<T> allValues(Function1<T, Boolean> predicate);
}
