package com.meti.compile;

import java.io.IOException;
import java.util.List;

public interface Compiler {
	<T> List<T> compile(Source source, Target<T> target) throws IOException, CompileException;

	@Deprecated
	String compile(Script script, String content) throws CompileException;
}
