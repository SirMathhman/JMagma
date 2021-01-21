package com.meti.compile.stage;

import com.meti.compile.CompileException;

public interface SingleStage<A, B> {
	B apply(A a) throws CompileException;
}