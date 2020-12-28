package com.meti.compile.feature;

import com.meti.compile.token.CompoundTokenizer;
import com.meti.compile.token.Node;
import com.meti.compile.token.TokenizationException;
import com.meti.compile.token.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.compile.feature.primitive.IntTokenizer.IntTokenizer_;
import static com.meti.compile.feature.scope.DeclarationTokenizer.DeclarationTokenizer_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompoundTokenizerTest {
	@Test
	void tokenize() throws TokenizationException {
		var impl = new Impl();
		assertEquals("I16 x;", impl.tokenize("const x : I16").orElseThrow().render());
		assertEquals("5", impl.tokenize("5").orElseThrow().render());
	}

	private static class Impl extends CompoundTokenizer<Node> {
		@Override
		protected List<Tokenizer<Node>> listChildren() {
			return List.of(DeclarationTokenizer_, IntTokenizer_);
		}
	}
}