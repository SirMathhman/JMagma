package com.meti.compile.feature.function;

import com.meti.compile.token.TokenizationException;
import com.meti.compile.token.Node;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.content.ContentNode.ContentNode;
import static com.meti.compile.feature.function.Invocation.Invocation;
import static com.meti.compile.feature.function.InvocationTokenizer.InvocationTokenizer_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InvocationTokenizerTest {
	private Node createNode() {
		return Invocation()
				.withCaller(ContentNode("myFunction"))
				.withArgument(ContentNode("10"))
				.withArgument(ContentNode("20"))
				.complete();
	}

	@Test
	void tokenize() throws TokenizationException {
		assertEquals(createNode(), InvocationTokenizer_.tokenize("myFunction(10, 20)").orElseThrow());
	}
}