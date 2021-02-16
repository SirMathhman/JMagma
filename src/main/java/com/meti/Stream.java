package com.meti;

public interface Stream<T> {
	boolean allMatch(F1<T, Boolean> predicate) throws StreamException;

	default <R> R fold(R initial, F2<R, T, R> folder) throws EndOfStreamException {
		throw new UnsupportedOperationException();
	}

	default Option<T> fold(F2<T, T, T> folder) throws EndOfStreamException {
		throw new UnsupportedOperationException();
	}

	T head() throws StreamException;

	default <R> Stream<R> map(F1<T, R> mapper) {
		throw new UnsupportedOperationException();
	}

	<R> Stream<R> mapE1(F1E1<T, R, ?> mapper);
}
