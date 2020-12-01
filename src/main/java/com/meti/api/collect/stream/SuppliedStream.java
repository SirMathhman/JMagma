package com.meti.api.collect.stream;

import com.meti.api.core.Option;
import com.meti.api.extern.ExceptionalFunction0;
import com.meti.api.extern.Function0;

public class SuppliedStream<T> extends AbstractStream<T> {
	private final ExceptionalFunction0<Option<T>, StreamException> supplier;

	public SuppliedStream(ExceptionalFunction0<Option<T>, StreamException> supplier) {
		this.supplier = supplier;
	}

	@Override
	protected Option<T> next() throws StreamException {
		return supplier.apply();
	}
}
