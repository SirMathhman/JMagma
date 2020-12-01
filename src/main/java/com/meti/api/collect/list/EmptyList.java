package com.meti.api.collect.list;

import com.meti.api.collect.IndexException;

public class EmptyList<T> implements List<T> {
	private EmptyList() {
	}

	public static <T> EmptyList<T> EmptyList() {
		return new EmptyList<>();
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public T get(int index) throws IndexException {
		throw IndexException.IndexException("List is empty.");
	}

	@Override
	public boolean isEmpty() {
		return true;
	}
}
