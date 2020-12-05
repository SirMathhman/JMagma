package com.meti.api.collect.stream;

import com.meti.api.extern.ExceptionFunction0;

public class DelegatedStream<T> extends SuppliedStream<T> {
	private final ExceptionFunction0<T, StreamException> function;

	public DelegatedStream(ExceptionFunction0<T, StreamException> function) {
		this.function = function;
	}

	@Override
	protected T get() throws StreamException {
		return function.get();
	}
}
