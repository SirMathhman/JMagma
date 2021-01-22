package com.meti.api.java.core;

import com.meti.api.magma.core.*;
import com.meti.api.magma.except.Exception;

import java.util.Optional;

public record JavaOption<T>(Optional<T> value) implements com.meti.api.magma.core.Option<T> {
	@Override
	public Option<T> filter(F1<T, Boolean> predicate) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <E extends Exception> void ifPresent(C1E1<T, E> actor) throws E {
		if (value.isPresent()) actor.accept(value.get());
	}

	@Override
	public <R> Option<R> map(F1<T, R> mapper) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <R, E extends Exception> Option<R> mapE1(F1E1<T, R, E> mapper) throws E {
		throw new UnsupportedOperationException();
	}

	@Override
	public T orElseGet(Supplier<T> supplier) {
		throw new UnsupportedOperationException();
	}
}
