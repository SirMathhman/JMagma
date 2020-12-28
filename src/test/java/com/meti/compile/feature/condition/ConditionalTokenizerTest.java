package com.meti.compile.feature.condition;

import com.meti.compile.token.TokenizationException;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.condition.Conditional.If;
import static com.meti.compile.feature.condition.ConditionalTokenizer.ConditionalTokenizer_;
import static com.meti.compile.feature.content.ContentNode.ContentNode;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConditionalTokenizerTest {

	@Test
	void tokenize() throws TokenizationException {
		var expected = If(ContentNode("true"), ContentNode("{}"));
		var actual = ConditionalTokenizer_.tokenize("if(true){}").orElseThrow();
		assertEquals(expected, actual);
	}
}