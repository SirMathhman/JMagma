package com.meti.compile.feature.field;

import com.meti.compile.feature.primitive.Int;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.meti.compile.feature.field.EmptyField.EmptyField;
import static com.meti.compile.feature.field.Field.Flag.CONST;
import static com.meti.compile.feature.field.Field.Flag.LET;
import static com.meti.compile.feature.field.FieldBuilder.FieldBuilder;
import static com.meti.compile.feature.primitive.Primitive.U16;
import static com.meti.compile.feature.primitive.Primitive.U8;
import static com.meti.compile.feature.field.ValueField.ValueField;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldBuilderTest {
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
		assertEquals(ValueField(Set.of(CONST), "x", U16, Int.Int(10)), FieldBuilder()
				.withFlag(CONST)
				.withName("x")
				.withType(U16)
				.withValue(Int.Int(10))
				.complete());
	}
}