package com.meti.api.java.core;

import com.meti.api.magma.core.C1E1;
import com.meti.api.magma.core.F1;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Supplier;
import com.meti.api.magma.except.Exception;

import java.util.Optional;

public record JavaOption<T>(Optional<T> value) implements com.meti.api.magma.core.Option<T> {
	@Override
	public <E extends Exception> void ifPresent(C1E1<T, E> actor) throws E {
		if (value.isPresent()) actor.accept(value.get());
	}

	@Override
	public <R> Option<R> map(F1<T, R> mapper) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T orElseGet(Supplier<T> supplier) {
		throw new UnsupportedOperationException();
	}
}
