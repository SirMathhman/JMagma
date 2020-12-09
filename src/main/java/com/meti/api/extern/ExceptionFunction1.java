package com.meti.api.extern;

public interface ExceptionFunction1<T, R, E extends Exception> {
	R apply(T t) throws E;
}
