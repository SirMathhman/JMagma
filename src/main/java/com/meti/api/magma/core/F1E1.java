package com.meti.api.magma.core;

public interface F1E1<A, B, C extends Exception> {
	B apply(A a) throws C;
}
