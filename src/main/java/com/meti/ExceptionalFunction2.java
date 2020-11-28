package com.meti;

public interface ExceptionalFunction2<A, B, C, E extends Exception> {
	C apply(A a, B b) throws E;
}
