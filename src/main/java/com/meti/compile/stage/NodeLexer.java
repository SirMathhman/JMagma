package com.meti.compile.stage;

import com.meti.compile.token.Token;

import java.util.List;

import static com.meti.compile.feature.function.FunctionNodeLexer_.FunctionNodeLexer_;
import static com.meti.compile.feature.structure.StructureNodeLexer.StructureNodeLexer_;

public class NodeLexer extends CompoundLexer<Token> {
	public static final Lexer<Token> NodeLexer_ = new NodeLexer();

	private NodeLexer() {
	}

	@Override
	protected List<Lexer<Token>> listLexers() {
		return List.of(StructureNodeLexer_, FunctionNodeLexer_);
	}
}
