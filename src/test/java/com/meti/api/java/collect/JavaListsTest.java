package com.meti.api.java.collect;

import com.meti.api.magma.collect.Sequences;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class JavaListsTest {

	@Test
	void fromJava() {
		var expected = List.of("first", "second");
		var inverted = JavaLists.fromJava(expected);
		var actual = JavaLists.toJava(inverted);
		assertIterableEquals(expected, actual);
	}

	@Test
	void toJava() {
		var sequence = Sequences.of("first", "second");
		var expected = List.of("first", "second");
		var actual = JavaLists.toJava(sequence);
		assertIterableEquals(expected, actual);
	}
}