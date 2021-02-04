package com.meti.api.magma.core;

public record Some<T>(T value) implements Option<T> {
	@Override
	public T orElse(T other) {
		return value;
	}
}
