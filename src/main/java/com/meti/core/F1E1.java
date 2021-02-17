package com.meti.core;

public interface F1E1<T, R, E extends Exception> {
	R apply(T t) throws E;
}
