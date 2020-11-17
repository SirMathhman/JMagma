package com.meti.compile.call.function;

import com.meti.compile.Type;
import com.meti.compile.content.ContentType;
import com.meti.compile.tokenize.TokenizeException;
import org.junit.jupiter.api.Test;

import static com.meti.compile.call.function.FunctionType.FunctionType;
import static org.junit.jupiter.api.Assertions.*;

class FunctionTypeTokenizerTest {
	@Test
	void mapping() throws TokenizeException {
		Type expected = FunctionType()
				.withParameter(new ContentType("U8"))
				.withReturn(new ContentType("I16"))
				.complete();
		Type actual = new FunctionTypeTokenizer("(U8) => I16")
				.tokenizeExceptionally()
				.orElseThrow();
		assertEquals(expected, actual);
	}
}