package com.meti.compile.lex;

import com.meti.api.magma.collect.stream.Stream;
import com.meti.api.magma.collect.stream.Streams;
import com.meti.compile.feature.primitive.PrimitiveLexer;
import com.meti.compile.token.Token;

public class MagmaTypeLexer extends CompoundLexer {
	static final Lexer<Token> MagmaTypeLexer_ = new MagmaTypeLexer();

	private MagmaTypeLexer() {
	}

	@Override
	protected Stream<Lexer<Token>> streamLexers() {
		return Streams.of(PrimitiveLexer.PrimitiveLexer_);
	}
}