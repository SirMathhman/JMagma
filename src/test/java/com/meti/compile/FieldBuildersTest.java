package com.meti.compile;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.meti.compile.EmptyField.EmptyField;
import static com.meti.compile.Field.Flag.CONST;
import static com.meti.compile.Field.Flag.LET;
import static com.meti.compile.FieldBuilders.FieldBuilder;
import static com.meti.compile.Int.Int;
import static com.meti.compile.Primitive.U16;
import static com.meti.compile.Primitive.U8;
import static com.meti.compile.ValueField.ValueField;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldBuildersTest {
	@Test
	void withoutValue() {
		assertEquals(EmptyField(Set.of(LET), "x", U8), FieldBuilder()
				.withFlag(LET)
				.withName("x")
				.withType(U8)
				.complete());
	}

	@Test
	void withValue() {
		assertEquals(ValueField(Set.of(CONST), "x", U16, Int(10)), FieldBuilder()
				.withFlag(CONST)
				.withName("x")
				.withType(U16)
				.withValue(Int(10))
				.complete());
	}
}