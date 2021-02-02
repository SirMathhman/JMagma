package com.meti.compile.feature.scope;

import com.meti.api.magma.core.Option;
import com.meti.compile.token.Input;

public interface Lexer<T> {
	Option<T> lex(Input input);
}
