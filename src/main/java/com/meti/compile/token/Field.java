package com.meti.compile.token;

import com.meti.core.F1;
import com.meti.core.F1E1;

import java.util.Optional;

public interface Field {
	enum Flag {
		CONST,
		LET
	}

	<R> R applyToName(F1<Input, R> mapper);

	<R, E extends Exception> R applyToNameE1(F1E1<Input, R, E> mapper) throws E;

	<R> R applyToType(F1<Token, R> mapper);

	<R, E extends Exception> R applyToTypeE1(F1E1<Token, R, E> mapper) throws E;

	<R> Optional<R> applyToValue(F1<Token, R> mapper);

	<R, E extends Exception> Optional<R> applyToValueE1(F1E1<Token, R, E> mapper) throws E;

	boolean isNamed(String name);

	<E extends Exception> Field mapByType(F1E1<Token, Token, E> mapper) throws E;

	<E extends Exception> Field mapByValue(F1E1<Token, Token, E> mapper) throws E;

	<E extends Exception> boolean testTypeE1(F1E1<Token, Boolean, E> predicate) throws E;

	Field withType(Token type);
}