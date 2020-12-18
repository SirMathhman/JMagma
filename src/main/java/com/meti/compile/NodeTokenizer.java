package com.meti.compile;

import com.meti.compile.feature.CompoundTokenizer;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.Tokenizer;

import java.util.List;

import static com.meti.compile.feature.primitive.IntTokenizer.IntTokenizer_;
import static com.meti.compile.feature.scope.DeclarationTokenizer.DeclarationTokenizer_;

class NodeTokenizer extends CompoundTokenizer<Node> {
	static final NodeTokenizer NodeTokenizer_ = new NodeTokenizer();

	private NodeTokenizer() {
	}

	@Override
	protected List<Tokenizer<Node>> listChildren() {
		return List.of(DeclarationTokenizer_, IntTokenizer_);
	}
}
