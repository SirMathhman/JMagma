package com.meti.compile;

import java.io.IOException;
import java.util.List;

public interface Compiler<C, R> {
	List<R> compile(Source source, Target<C, R> target) throws IOException, CompileException;
}
