package com.meti;

public interface ExceptionalFunction1<T, R, E extends Exception> {
	R apply(T t) throws E;
}
