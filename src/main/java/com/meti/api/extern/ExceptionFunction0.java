package com.meti.api.extern;

public interface ExceptionFunction0<T, E extends Exception> {
	T get() throws E;
}
