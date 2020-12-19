package com.meti.compile.feature.function;

import com.meti.compile.TokenizationException;
import com.meti.compile.feature.content.ContentNode;
import com.meti.compile.feature.content.ContentType;
import com.meti.compile.feature.field.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.content.ContentNode.ContentNode;
import static com.meti.compile.feature.content.ContentType.ContentType;
import static com.meti.compile.feature.field.Field.Flag.DEF;
import static com.meti.compile.feature.function.FunctionTokenizer.FunctionTokenizer_;
import static org.junit.jupiter.api.Assertions.*;

class FunctionTokenizerTest {

	@Test
	void tokenize() throws TokenizationException {
		var expected = FunctionBuilder.withFlag(DEF)
				.withName("main")
				.withReturn(ContentType("I16"))
				.withValue(ContentNode("{return 0;}"))
				.complete();
		var actual = FunctionTokenizer_.tokenize("def main() : I16 => {return 0;}")
				.orElseThrow();
		assertEquals(expected, actual);
	}
}