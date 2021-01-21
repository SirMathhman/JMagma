package com.meti.compile.stage;

import com.meti.compile.token.Token;

import java.util.List;

import static com.meti.compile.feature.primitive.PrimitiveLexer.PrimitiveLexer_;

public class TypeLexer extends CompoundLexer<Token> {
	public static final Lexer<Token> TypeLexer_ = new TypeLexer();

	private TypeLexer() {
	}

	@Override
	protected List<Lexer<Token>> listLexers() {
		return List.of(PrimitiveLexer_);
	}
}
