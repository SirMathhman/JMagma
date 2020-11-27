package com.meti;

public class None<T> implements Option<T> {
	private None() {
	}

	public static <T> None<T> None() {
		return new None<T>();
	}

	@Override
	public T orElse(T other) {
		return other;
	}
}
