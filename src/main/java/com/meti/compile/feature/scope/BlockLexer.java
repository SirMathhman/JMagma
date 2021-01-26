package com.meti.compile.feature.scope;

import com.meti.compile.Compiler;

public class BlockLexer implements Lexer {
	public static final Lexer BlockLexer_ = new BlockLexer();

	private BlockLexer() {
	}

	@Override
	public boolean canLex(String line) {
		return line.startsWith("{") && line.endsWith("}");
	}

	@Override
	public String lex(String content, Compiler compiler) {
		var length = content.length();
		var slice = content.substring(1, length - 1);
		var string = slice.trim();
		var body = compiler.compileAll(string);
		return "{%s}".formatted(body);
	}
}