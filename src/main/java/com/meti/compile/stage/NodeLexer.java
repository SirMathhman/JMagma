package com.meti.compile.stage;

import com.meti.compile.token.Token;

import java.util.List;

import static com.meti.compile.feature.block.BlockLexer.BlockLexer_;
import static com.meti.compile.feature.function.FunctionNodeLexer.FunctionNodeLexer_;
import static com.meti.compile.feature.function.ReturnLexer.ReturnLexer_;
import static com.meti.compile.feature.primitive.IntegerLexer.IntegerLexer_;
import static com.meti.compile.feature.structure.StructureNodeLexer.StructureNodeLexer_;

public class NodeLexer extends CompoundLexer<Token> {
	public static final Lexer<Token> NodeLexer_ = new NodeLexer();

	private NodeLexer() {
	}

	@Override
	protected List<Lexer<Token>> listLexers() {
		return List.of(
				ReturnLexer_,
				StructureNodeLexer_,
				BlockLexer_,
				FunctionNodeLexer_,
				IntegerLexer_);
	}
}
