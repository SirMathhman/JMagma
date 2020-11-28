package com.meti.api.extern;

public interface ExceptionalFunction0<T, E extends Exception> {
	T apply() throws E;
}
