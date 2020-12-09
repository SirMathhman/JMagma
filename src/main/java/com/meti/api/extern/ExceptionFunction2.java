package com.meti.api.extern;

public interface ExceptionFunction2<A, B, C, E extends Exception> {
	C apply(A a, B b) throws E;
}
