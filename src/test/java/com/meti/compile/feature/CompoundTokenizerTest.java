package com.meti.compile.feature;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static com.meti.compile.feature.primitive.IntTokenizer.IntTokenizer_;
import static com.meti.compile.feature.scope.DeclarationTokenizer.DeclarationTokenizer_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompoundTokenizerTest {
	@Test
	void tokenize() {
		var impl = new Impl();
		assertEquals("I16 x;", impl.tokenize("const x : I16").orElseThrow().render());
		assertEquals("5", impl.tokenize("5").orElseThrow().render());
	}

	private static class Impl extends CompoundTokenizer<Node> {
		@Override
		protected Stream<Tokenizer<Node>> streamChildren() {
			return Stream.of(DeclarationTokenizer_, IntTokenizer_);
		}
	}
}