package com.meti.compile;

import com.meti.compile.feature.Node;

public interface Cache<T extends Enum<T>> {
	String render();

	Cache<T> put(T type, Node node);
}
