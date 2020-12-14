package com.meti.api.collect.stream;

import com.meti.api.extern.ExceptionFunction0;
import com.meti.api.extern.ExceptionFunction1;

public class SuppliedStream<T> extends DelegatedStream<T> {
	private final ExceptionFunction0<T, StreamException> function;

	public SuppliedStream(ExceptionFunction0<T, StreamException> function) {
		this.function = function;
	}

	@Override
	protected T get() throws StreamException {
		return function.get();
	}

}
