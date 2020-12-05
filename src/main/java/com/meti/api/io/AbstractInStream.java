package com.meti.api.io;

import com.meti.api.extern.ExceptionFunction1;

public abstract class AbstractInStream implements InStream {
	@Override
	public <R, E extends Exception> R enclosing(ExceptionFunction1<InStream, R, E> mapper) throws E {
		return mapper.apply(this);
	}
}
