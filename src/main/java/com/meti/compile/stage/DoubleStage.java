package com.meti.compile.stage;

import com.meti.compile.CompileException;

public interface DoubleStage<A, B, C> {
	C apply(A a, B b) throws CompileException;
}