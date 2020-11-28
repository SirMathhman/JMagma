package com.meti;

public interface ExceptionalFunction0<T, E extends Exception> {
	T apply() throws E;
}
