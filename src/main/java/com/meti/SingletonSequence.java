package com.meti;

public class SingletonSequence<T> implements Sequence<T, List<T>> {
	private final T value;

	public SingletonSequence(T value) {
		this.value = value;
	}

	@Override
	public <R> List<R> empty() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public T get(int index) throws IndexException {
		return null;
	}

	public List<T> add(T t) {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
