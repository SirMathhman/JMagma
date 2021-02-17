package com.meti;

import java.util.Optional;

public interface Field {
	<R, E extends Exception> R applyToNameE1(F1E1<String, R, E> mapper) throws E;

	<R, E extends Exception> R applyToTypeE1(F1E1<Token, R, E> mapper) throws E;

	<R, E extends Exception> Optional<R> applyToValueE1(F1E1<Token, R, E> mapper) throws E;

	<R> Optional<R> applyToValue(F1<Token, R> mapper);

	boolean testType(F1<Token, Boolean> predicate);

	<E extends Exception> boolean testTypeE1(F1E1<Token, Boolean, E> predicate) throws E;
}