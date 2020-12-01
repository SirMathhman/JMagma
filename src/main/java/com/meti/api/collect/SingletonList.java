package com.meti.api.collect;

import java.util.Objects;

public class SingletonList<T> implements List<T> {
	private final T value;

	private SingletonList(T value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SingletonList<?> that = (SingletonList<?>) o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
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
		else throw IndexException.IndexException("Index is not zero.");
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
