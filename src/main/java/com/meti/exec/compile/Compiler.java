package com.meti.exec.compile;

public interface Compiler {
	Result<Result.Group> compiler(String content) throws CompileException;
}
