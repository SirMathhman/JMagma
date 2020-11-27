package com.meti;

public class Some<T> implements Option<T> {
	private final T value;

	private Some(T value) {
		this.value = value;
	}

	public static <T> Some<T> Some(T value) {
		return new Some<>(value);
	}

	@Override
	public T orElse(T other) {
		return value;
	}
}
