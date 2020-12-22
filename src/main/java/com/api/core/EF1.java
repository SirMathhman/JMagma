package com.api.core;

public interface EF1<A, B, C extends Exception> {
	B apply(A a) throws C;
}
