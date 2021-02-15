package com.meti.compile;

import com.meti.api.core.Option;
import com.meti.compile.token.Input;
import com.meti.compile.token.Token;

public interface Lexer {
	Option<Token> lex(Input input);
}
