package com.meti.compile.feature.block;

import com.meti.compile.token.TokenizationException;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.block.Block.Block;
import static com.meti.compile.feature.block.BlockTokenizer.BlockTokenizer_;
import static com.meti.compile.feature.content.ContentNode.ContentNode;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockTokenizerTest {

	@Test
	void tokenize() throws TokenizationException {
		assertEquals(Block(ContentNode("return 10")), BlockTokenizer_.tokenize("{return 10;}").orElseThrow());
	}

	@Test
	void tokenizeMultiple() throws TokenizationException {
		assertEquals(Block(ContentNode("const x : I16 = 10"), ContentNode("return x")), BlockTokenizer_.tokenize("{const x : I16 = 10; return x;}").orElseThrow());
	}

	@Test
	void tokenizeBrackets() throws TokenizationException {
		assertEquals(Block(ContentNode("{}"), ContentNode("{}")), BlockTokenizer_.tokenize("{{}{}}").orElseThrow());
	}
}