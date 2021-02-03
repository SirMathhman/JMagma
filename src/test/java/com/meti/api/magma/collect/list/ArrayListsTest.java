package com.meti.api.magma.collect.list;

import com.meti.api.magma.collect.IndexException;
import com.meti.api.magma.collect.stream.StreamException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListsTest {
	@Test
	void apply() throws IndexException {
		assertEquals("test1", createList().apply(1));
	}

	private List<String> createList() {
		return ArrayLists.of("test0", "test1", "test2");
	}

	@Test
	void apply_negative() {
		assertThrows(IndexException.class, () -> createList().apply(-1));
	}

	@Test
	void apply_positive() {
		assertThrows(IndexException.class, () -> createList().apply(3));
	}

	@Test
	void set() throws IndexException {
		var expected = createExpected();
		var actual = createList().set(0, "test3");
		assertTrue(Lists.equalsTo(actual, expected, String::equals));
	}

	private List<String> createExpected() {
		return ArrayLists.of("test0", "test3", "test2");
	}

	@Test
	void set_negative() {
		assertThrows(IndexException.class, () -> createList().set(-1, "test"));
	}

	@Test
	void set_positive() throws IndexException {
		var expected = ArrayLists.of("test0", "test1", "test2");
		var actual = ArrayLists.of("test0", "test1").set(2, "test2");
		assertTrue(Lists.equalsTo(expected, actual, String::equals));
	}

	@Test
	void size() {
		assertEquals(3, createList().size());
	}

	@Test
	void stream() throws StreamException {
		var expected = createList();
		var actual = expected.stream().fold(ArrayLists.<String>empty(), List::add);
		assertTrue(Lists.equalsTo(expected, actual, String::equals));
	}
}