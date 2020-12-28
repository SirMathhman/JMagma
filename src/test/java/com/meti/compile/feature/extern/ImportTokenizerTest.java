package com.meti.compile.feature.extern;

import com.meti.compile.token.TokenizationException;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.extern.ImportTokenizer.ImportTokenizer_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImportTokenizerTest {

	@Test
	void tokenize() throws TokenizationException {
		assertEquals(Directives.Include.toNode("<stdio.h>"), ImportTokenizer_.tokenize("import native stdio").orElseThrow());
	}
}