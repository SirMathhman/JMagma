package com.meti.compile;

import com.meti.compile.script.Source;
import com.meti.compile.script.Target;

import java.io.IOException;
import java.util.List;

public interface Compiler<C, R> {
	List<R> compile(Source source, Target<C, R> target) throws IOException, CompileException;
}
