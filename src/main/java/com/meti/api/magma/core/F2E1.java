package com.meti.api.magma.core;

public interface F2E1<A, B, R, E extends Exception> {
	R apply(A a, B b) throws E;
}
