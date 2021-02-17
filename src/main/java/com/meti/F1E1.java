package com.meti;

public interface F1E1<T, R, E extends Exception> {
	R apply(T t) throws E;
}
