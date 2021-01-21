package com.meti.api.magma.core;

import com.meti.api.magma.except.Exception;

public interface C1E1<T, E extends Exception> {
	void accept(T value) throws E;
}
