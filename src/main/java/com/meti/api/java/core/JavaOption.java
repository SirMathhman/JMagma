package com.meti.api.java.core;

import java.util.Optional;

public record JavaOption<T>(Optional<T> value) implements com.meti.api.magma.core.Option<T> {
	@Override
	public T orElse(T other) {
		return value.orElse(other);
	}
}
