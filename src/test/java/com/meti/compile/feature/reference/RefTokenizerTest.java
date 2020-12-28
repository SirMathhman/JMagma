package com.meti.compile.feature.reference;

import com.meti.compile.token.TokenizationException;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.content.ContentType.ContentType;
import static com.meti.compile.feature.reference.RefTokenizer.RefTokenizer_;
import static com.meti.compile.feature.reference.RefType.RefType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RefTokenizerTest {

	@Test
	void tokenize() throws TokenizationException {
		assertEquals(RefType(ContentType("I64")), RefTokenizer_.tokenize("Ref[I64]").orElseThrow());
	}
}