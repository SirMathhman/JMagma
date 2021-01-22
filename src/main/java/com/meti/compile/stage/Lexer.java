package com.meti.compile.stage;

import com.meti.api.magma.core.Option;
import com.meti.compile.CompileException;

public interface Lexer<T> {
	Option<T> lex(String content) throws CompileException;
}
