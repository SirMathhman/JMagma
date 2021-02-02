package com.meti.compile;

import com.meti.compile.feature.primitive.PrimitiveLexer;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.token.Token;

import java.util.stream.Stream;

public class MagmaTypeLexer extends CompoundLexer {
	static final Lexer<Token> MagmaTypeLexer_ = new MagmaTypeLexer();

	private MagmaTypeLexer() {
	}

	@Override
	protected Stream<Lexer<Token>> streamLexers() {
		return Stream.of(
				PrimitiveLexer.PrimitiveLexer_
		);
	}
}