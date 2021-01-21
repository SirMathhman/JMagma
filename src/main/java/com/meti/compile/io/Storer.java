package com.meti.compile.io;

import com.meti.api.magma.io.IOException_;
import com.meti.compile.CompileException;

public interface Storer<T> {
	T write(Result result) throws IOException_, CompileException;
}
