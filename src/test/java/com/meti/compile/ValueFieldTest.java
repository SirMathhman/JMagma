package com.meti.compile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.meti.compile.Int.Int;
import static com.meti.compile.ValueField.ValueField;

class ValueFieldTest {

	@Test
	void render() {
		Assertions.assertEquals("int x=10", ValueField(Set.of(Field.Flag.CONST), "x", Primitive.I16, Int(10)).render());
	}
}