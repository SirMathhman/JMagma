package com.meti.compile.cache;

import com.meti.compile.token.Node;

public interface Cache<T extends Enum<T>> {
	String render();

	Cache<T> put(T type, Node node);
}
