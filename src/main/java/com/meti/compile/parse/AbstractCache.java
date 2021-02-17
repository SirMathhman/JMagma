package com.meti.compile.parse;

public abstract class AbstractCache<T> implements Cache<T> {
	protected final Stack stack;

	public AbstractCache(Stack stack) {
		this.stack = stack;
	}
}
