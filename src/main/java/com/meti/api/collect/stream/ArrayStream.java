package com.meti.api.collect.stream;

import com.meti.api.extern.ExceptionFunction1;

import static com.meti.api.collect.stream.EndOfStreamException.EndOfStreamException;

public class ArrayStream<T> extends DelegatedStream<T> {
	private final T[] array;
	private int counter = 0;

	private ArrayStream(T[] array) {
		this.array = array;
	}

	public static <T> ArrayStream<T> ArrayStream(T[] array) {
		return new ArrayStream<T>(array);
	}

	@Override
	protected T get() throws StreamException {
		if (counter < array.length) {
			return array[counter++];
		} else {
			throw EndOfStreamException("No more elements left.");
		}
	}

	@Override
	public <R, E extends Exception> Stream<R> mapExceptionally(ExceptionFunction1<T, R, E> mapper) throws StreamException {
		return mapExceptionally(null);
	}
}
