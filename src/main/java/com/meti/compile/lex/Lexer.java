package com.meti.compile.lex;

import com.meti.api.magma.core.Option;
import com.meti.compile.token.Input;

public interface Lexer<T> {
	Option<T> lex(Input input);
}
