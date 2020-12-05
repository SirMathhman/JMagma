package com.meti.api.io;

import com.meti.api.extern.ExceptionFunction1;

public abstract class AbstractOutStream implements OutStream {
	@Override
	public <R, E extends Exception> R enclosing(ExceptionFunction1<OutStream, R, E> mapper) throws E {
		return mapper.apply(this);
	}
}
