package com.meti.compile.feature.scope;

import com.meti.compile.Compiler;
import com.meti.compile.token.Token;

public interface Lexer {
	boolean canLex(String line);

	Token lex(String content);
}
