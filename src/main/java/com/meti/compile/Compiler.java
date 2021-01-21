package com.meti.compile;

import com.meti.compile.io.Loader;
import com.meti.compile.io.Result;

public interface Compiler {
	Result compile(Loader loader) throws CompileException;
}
