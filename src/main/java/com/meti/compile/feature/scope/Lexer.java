package com.meti.compile.feature.scope;

import com.meti.compile.Compiler;

public interface Lexer {
	boolean canLex(String line);

	String lex(String content, Compiler compiler);
}
