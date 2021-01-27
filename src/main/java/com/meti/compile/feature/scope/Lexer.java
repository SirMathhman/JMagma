package com.meti.compile.feature.scope;

import com.meti.compile.Compiler;
import com.meti.compile.token.Content;

public interface Lexer {
	boolean canLex(String line);

	Content lex(String content, Compiler compiler);
}
