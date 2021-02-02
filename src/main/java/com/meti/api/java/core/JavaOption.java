package com.meti.api.java.core;

import com.meti.api.magma.core.F1;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Supplier;

import java.util.Optional;

public record JavaOption<T>(Optional<T> value) implements com.meti.api.magma.core.Option<T> {
	@Override
	public boolean isPresent(){
		throw new UnsupportedOperationException();
	}

	@Override
	public <R> Option<R> map(F1<T, R> mapper) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T orElse(T other) {
		return value.orElse(other);
	}

	@Override
	public T orElseGet(Supplier<T> supplier) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <E extends Exception> T orElseThrow(Supplier<E> supplier) {
		throw new UnsupportedOperationException();
	}
}
