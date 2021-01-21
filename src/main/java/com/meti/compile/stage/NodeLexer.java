package com.meti.compile.stage;

import com.meti.compile.token.Token;

import java.util.stream.Stream;

import static com.meti.compile.feature.structure.StructureNodeLexer.StructureNodeLexer_;

public class NodeLexer extends CompoundLexer<Token> {
	public static final Lexer<Token> NodeLexer_ = new NodeLexer();

	private NodeLexer() {
	}

	@Override
	protected Stream<Lexer<Token>> streamLexers() {
		return Stream.of(StructureNodeLexer_);
	}
}
