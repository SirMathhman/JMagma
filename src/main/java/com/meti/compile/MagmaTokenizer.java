package com.meti.compile;

import com.meti.compile.feature.CompoundTokenizer;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.Tokenizer;
import com.meti.compile.feature.primitive.IntTokenizer;
import com.meti.compile.feature.scope.DeclarationTokenizer;

import java.util.stream.Stream;

import static com.meti.compile.feature.primitive.IntTokenizer.IntTokenizer_;
import static com.meti.compile.feature.scope.DeclarationTokenizer.DeclarationTokenizer_;

class MagmaTokenizer extends CompoundTokenizer<Node> {
	static final MagmaTokenizer MagmaTokenizer_ = new MagmaTokenizer();

	private MagmaTokenizer() {
	}

	@Override
	protected Stream<Tokenizer<Node>> streamChildren() {
		return Stream.of(DeclarationTokenizer_, IntTokenizer_);
	}
}
