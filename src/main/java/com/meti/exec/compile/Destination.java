package com.meti.exec.compile;

public interface Destination<T> {
	String apply(Package p, T type);
}
