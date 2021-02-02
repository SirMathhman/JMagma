package com.meti.compile.feature.scope;

import com.meti.api.magma.core.Option;

public interface Lexer<T> {
	Option<T> lex(String content);
}
