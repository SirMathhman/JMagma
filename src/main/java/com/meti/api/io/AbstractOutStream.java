package com.meti.api.io;

import com.meti.api.extern.ExceptionFunction1;

import java.io.IOException;

public abstract class AbstractOutStream implements OutStream {
	@Override
	public <R, E extends Exception> R enclosing(ExceptionFunction1<OutStream, R, E> mapper) throws E, IOException {
		var result = mapper.apply(this);
		flush();
		close();
		return result;
	}
}
