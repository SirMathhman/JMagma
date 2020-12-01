package com.meti.api.collect.list;

import com.meti.api.collect.Collection;
import com.meti.api.collect.IndexException;

public interface List<T> extends Collection {
	T get(int index) throws IndexException;
}

