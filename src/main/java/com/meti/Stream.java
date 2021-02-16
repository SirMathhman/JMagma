package com.meti;

public interface Stream<T> {
	boolean allMatch(F1<T, Boolean> predicate) throws StreamException;

	<R> R fold(R initial, F2<R, T, R> folder) throws EndOfStreamException;

	Option<T> fold(F2<T, T, T> folder) throws EndOfStreamException;

	T head() throws StreamException;

	<R> Stream<R> map(F1<T, R> mapper);

	<R> Stream<R> mapE1(F1E1<T, R, ?> mapper);
}
