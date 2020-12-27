package com.meti.compile;

import java.io.IOException;
import java.util.List;

public interface Compiler<T, R> {
	List<R> compile(Source source, Target<T, R> target) throws IOException, CompileException;
}
