package com.meti.exec.compile.render.field;

import com.meti.MagmaTest;
import com.meti.api.extern.Action1;
import com.meti.exec.compile.render.ImplicitType;
import com.meti.exec.compile.render.TokenizationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.meti.exec.compile.render.ContentNode.ContentNode;
import static com.meti.exec.compile.render.field.FieldBuilders.FieldBuilder;
import static com.meti.exec.compile.render.field.FieldTokenizer.FieldTokenizer;
import static org.junit.jupiter.api.Assertions.*;

class FieldTokenizerTest extends MagmaTest {
	@Test
	void tokenizeBoth() throws TokenizationException {
		assertEquals("I16 x=10", FieldTokenizer("const x : I16 = 10")
				.tokenize()
				.flatMap(Field::render)
				.orElse(""));
	}

	@Test
	void tokenizeType() throws TokenizationException {
		assertEquals("I16 x", FieldTokenizer("const x : I16")
				.tokenize()
				.flatMap(Field::render)
				.orElse(""));
	}

	@Test
	void tokenizeDefaultValue() throws TokenizationException {
		Action1<Field> action = field -> assertStringablesEqual(createDummy(), field);
		FieldTokenizer("const x = 10")
				.tokenize()
				.ifPresentOrElse(action, Assertions::fail);
	}

	private Field createDummy() {
		return FieldBuilder()
				.withFlag(Field.Flag.CONST)
				.withName("x")
				.withType(ImplicitType.ImplicitType_)
				.withValue(ContentNode("10"))
				.complete();
	}

	@Test
	void tokenizeInvalid() {
		assertThrows(TokenizationException.class, () -> FieldTokenizer("const x").tokenize());
	}
}