package com.meti.exec.compile.render.field;

import com.meti.exec.compile.render.ImplicitType;
import org.junit.jupiter.api.Test;

import static com.meti.exec.compile.render.ContentNode.ContentNode;
import static com.meti.exec.compile.render.field.FieldBuilders.FieldBuilder;
import static org.junit.jupiter.api.Assertions.*;

class FieldWithValueTest {

	@Test
	void equalsTo() {
		var field = FieldBuilder()
				.withFlag(Field.Flag.CONST)
				.withName("x")
				.withType(ImplicitType.ImplicitType_)
				.withValue(ContentNode("10"))
				.complete();
		assertTrue(field.equalsTo(field));
	}
}