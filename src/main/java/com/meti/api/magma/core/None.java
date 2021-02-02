package com.meti.api.magma.core;

public class None<T> implements Option<T> {
	@Override
	public boolean isPresent() {
		return false;
	}

	@Override
	public T orElse(T other) {
		return other;
	}
}
