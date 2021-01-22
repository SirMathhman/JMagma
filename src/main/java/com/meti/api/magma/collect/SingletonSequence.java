package com.meti.api.magma.collect;

public record SingletonSequence<T>(T value) implements Sequence<T> {
	@Override
	public T apply(int index) throws IndexException {
		if (index == 0) return value;
		throw new IndexException("Not zero.");
	}

	@Override
	public int size() {
		return 1;
	}
}
