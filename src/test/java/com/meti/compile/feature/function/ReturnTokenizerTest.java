package com.meti.compile.feature.function;

import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.content.ContentNode.ContentNode;
import static com.meti.compile.feature.function.Return.Return;
import static com.meti.compile.feature.function.ReturnTokenizer.ReturnTokenizer_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReturnTokenizerTest {

	@Test
	void tokenize() {
		assertEquals(Return(ContentNode("0")), ReturnTokenizer_.tokenize("return 0").orElseThrow());
	}
}