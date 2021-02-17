package com.meti;

import java.util.List;

import static com.meti.DeclarationLexer.DeclarationLexer_;

public class MagmaNodeLexer extends CompoundLexer {
	public static final Lexer<Token> MagmaNodeLexer_ = new MagmaNodeLexer();

	public MagmaNodeLexer() {
	}

	@Override
	public List<Lexer<Token>> listLexers() {
		return List.of(DeclarationLexer_);
	}
}
