package com.meti.exec.compile.render.feature.primitive;

import com.meti.exec.compile.render.Node;
import com.meti.exec.compile.render.RenderException;
import org.junit.jupiter.api.Test;

import static com.meti.exec.compile.render.feature.primitive.IntegerTokenizer.IntegerTokenizer;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerTokenizerTest {
	@Test
	void tokenizeNegative() throws RenderException {
		assertEquals("-420", IntegerTokenizer("-420")
				.tokenize()
				.mapExceptionally(Node::render)
				.orElse(""));
	}

	@Test
	void tokenizePositive() throws RenderException {
		assertEquals("420", IntegerTokenizer("420")
				.tokenize()
				.mapExceptionally(Node::render)
				.orElse(""));
	}

	@Test
	void tokenizeZero() throws RenderException {
		assertEquals("0", IntegerTokenizer("0")
				.tokenize()
				.mapExceptionally(Node::render)
				.orElse(""));
	}
}