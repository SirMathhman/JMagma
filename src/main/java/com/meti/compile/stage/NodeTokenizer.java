package com.meti.compile.stage;

import com.meti.compile.token.CompoundTokenizer;
import com.meti.compile.token.Node;
import com.meti.compile.token.Tokenizer;

import java.util.List;

import static com.meti.compile.feature.block.BlockTokenizer.BlockTokenizer_;
import static com.meti.compile.feature.condition.BooleanTokenizer.BooleanTokenizer_;
import static com.meti.compile.feature.condition.ConditionalTokenizer.ConditionalTokenizer_;
import static com.meti.compile.feature.extern.ImportTokenizer.ImportTokenizer_;
import static com.meti.compile.feature.function.FunctionTokenizer.FunctionTokenizer_;
import static com.meti.compile.feature.function.InvocationTokenizer.InvocationTokenizer_;
import static com.meti.compile.feature.function.ReturnTokenizer.ReturnTokenizer_;
import static com.meti.compile.feature.primitive.IntTokenizer.IntTokenizer_;
import static com.meti.compile.feature.scope.DeclarationTokenizer.DeclarationTokenizer_;
import static com.meti.compile.feature.scope.VariableTokenizer.VariableTokenizer_;
import static com.meti.compile.feature.struct.StructureTokenizer.StructureTokenizer_;

class NodeTokenizer extends CompoundTokenizer<Node> {
	static final NodeTokenizer NodeTokenizer_ = new NodeTokenizer();

	private NodeTokenizer() {
	}

	@Override
	protected List<Tokenizer<Node>> listChildren() {
		return List.of(
				ImportTokenizer_,
				InvocationTokenizer_,
				StructureTokenizer_,
				BooleanTokenizer_,
				ConditionalTokenizer_,
				BlockTokenizer_,
				ReturnTokenizer_,
				FunctionTokenizer_,
				DeclarationTokenizer_,
				IntTokenizer_,
				VariableTokenizer_
		);
	}
}
