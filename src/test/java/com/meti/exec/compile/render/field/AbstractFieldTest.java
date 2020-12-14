package com.meti.exec.compile.render.field;

import com.meti.api.collect.list.ArrayList;
import com.meti.exec.compile.render.field.Field.Flag;
import org.junit.jupiter.api.Test;

import static com.meti.exec.compile.render.ImplicitType.ImplicitType_;
import static com.meti.exec.compile.render.field.FieldBuilders.FieldBuilder;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbstractFieldTest {

	@Test
	void isNamed() {
		assertTrue(FieldBuilder()
				.withName("test")
				.withType(ImplicitType_)
				.complete()
				.isNamed("test"));
	}

	@Test
	void isTyped() {
		assertTrue(FieldBuilder()
				.withName("test")
				.withType(ImplicitType_)
				.complete()
				.isTyped(ImplicitType_));
	}

	@Test
	void hasFlags() {
		assertTrue(FieldBuilder()
				.withFlag(Flag.Const)
				.withName("test")
				.withType(ImplicitType_)
				.complete()
				.hasFlags(ArrayList.of(Flag::equals, Flag.Const)));
	}
}