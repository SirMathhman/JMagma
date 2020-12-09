package com.meti.api.io;

import com.meti.api.extern.ExceptionFunction1;

import java.io.IOException;

public interface Closeable<C extends Closeable<? extends C>> {
	<R, E extends Exception> R enclosing(ExceptionFunction1<C, R, E> mapper) throws E, IOException;

	void close() throws IOException;
}
