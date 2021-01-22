package com.meti.api.magma.collect;

public class EmptySequence<T> implements Sequence<T> {
	@Override
	public T apply(int index) throws IndexException {
		throw new IndexException("Sequence is empty.");
	}

	@Override
	public int size() {
		return 0;
	}
}
