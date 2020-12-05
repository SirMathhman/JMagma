package com.meti.api.io;

import com.meti.api.collect.DelegatedStream;
import com.meti.api.collect.EndOfStreamException;
import com.meti.api.collect.Stream;
import com.meti.api.collect.StreamException;
import com.meti.api.extern.ExceptionFunction1;

import java.io.IOException;

import static com.meti.api.collect.StreamException.StreamException;

public abstract class AbstractInStream implements InStream {
	@Override
	public <R, E extends Exception> R enclosing(ExceptionFunction1<InStream, R, E> mapper) throws E, IOException {
		var result = mapper.apply(this);
		close();
		return result;
	}

	@Override
	public Stream<Integer> stream() {
		return new DelegatedStream<>(this::supply);
	}

	private int supply() throws StreamException {
		try {
			var value = read();
			if (value == -1) {
				throw new EndOfStreamException("End of in stream has been reached.");
			} else {
				return value;
			}
		} catch (IOException e) {
			throw StreamException("Cannot read from in stream.", e);
		}
	}
}
