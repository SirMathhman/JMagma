package com.meti.api.collect;

public class SingletonList<T> implements List<T> {
	private final T value;

	private SingletonList(T value) {
		this.value = value;
	}

	public static <T> SingletonList<T> SingletonList(T value) {
		return new SingletonList<>(value);
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public T get(int index) throws IndexException {
		if(index == 0) return value;
		else throw new IndexException("Index is not zero.");
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
