package com.meti.exec.compile.render.feature.primitive;

import com.meti.exec.compile.render.Node;
import org.junit.jupiter.api.Test;

import static com.meti.exec.compile.render.feature.primitive.IntegerTokenizer.IntegerTokenizer;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerTokenizerTest {
	@Test
	void tokenizeNegative(){
		assertEquals("-420", IntegerTokenizer("-420")
				.tokenize()
				.map(Node::render)
				.orElse(""));
	}

	@Test
	void tokenizePositive() {
		assertEquals("420", IntegerTokenizer("420")
				.tokenize()
				.map(Node::render)
				.orElse(""));
	}

	@Test
	void tokenizeZero() {
		assertEquals("0", IntegerTokenizer("0")
				.tokenize()
				.map(Node::render)
				.orElse(""));
	}
}