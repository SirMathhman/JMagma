package com.meti.compile.feature.struct;

import com.meti.compile.TokenizationException;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.field.Field;
import org.junit.jupiter.api.Test;

import static com.meti.compile.feature.content.ContentType.ContentType;
import static com.meti.compile.feature.field.FieldBuilder.FieldBuilder;
import static com.meti.compile.feature.struct.Structure.Structure;
import static com.meti.compile.feature.struct.StructureTokenizer.StructureTokenizer_;
import static org.junit.jupiter.api.Assertions.*;

class StructureTokenizerTest {
	private Node createStructure() {
		var field = FieldBuilder()
				.withFlag(Field.Flag.CONST)
				.withName("value")
				.withType(ContentType("I32"))
				.complete();
		return Structure()
				.withName("Wrapper")
				.withField(field)
				.complete();
	}

	@Test
	void tokenize() throws TokenizationException {
		assertEquals(createStructure(), StructureTokenizer_.tokenize("struct Wrapper {const value : I32}").orElseThrow());
	}
}