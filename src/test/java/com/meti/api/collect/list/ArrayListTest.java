package com.meti.api.collect.list;

import com.meti.api.collect.IndexException;
import com.meti.api.collect.string.Strings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArrayListTest {
	@Test
	void asArray() {
		assertArrayEquals(new Object[0], ArrayList.empty(Strings::equalsTo).asArray());
	}

	@Test
	void rangeEquals() throws IndexException {
		assertArrayEquals(new Object[]{}, ArrayList.range(3, 3, Integer::compareTo, i -> i + 1).asArray());
	}

	@Test
	void rangeValid() throws IndexException {
		assertArrayEquals(new Object[]{0, 1, 2}, ArrayList.range(0, 3, Integer::compareTo, i -> i + 1).asArray());
	}

	@Test
	void rangeInvalid() {
		assertThrows(IndexException.class, () -> ArrayList.range(3, 0, Integer::compareTo, i -> i + 1));
	}
}