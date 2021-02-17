package com.meti;

import java.util.Collections;
import java.util.List;

public class MagmaTypeLexer extends CompoundLexer {
	public static final Lexer<Token> MagmaTypeLexer_ = new MagmaTypeLexer();

	@Override
	public List<Lexer<Token>> listLexers() {
		return Collections.emptyList();
	}
}
