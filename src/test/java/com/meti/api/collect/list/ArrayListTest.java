package com.meti.api.collect.list;

import com.meti.api.collect.IndexException;
import com.meti.api.collect.string.Strings;
import com.meti.api.core.Primitives;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.list.ArrayList.of;
import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
	@Test
	void range() throws IndexException {
		var list = ArrayList.range(1, 4, Primitives::comparingInts, i -> i + 1);
		assertEquals(3, list.size());
		assertEquals(1, list.apply(0));
		assertEquals(2, list.apply(1));
		assertEquals(3, list.apply(2));
	}

	@Test
	void applyLower() {
		assertThrows(IndexException.class, () -> createList().apply(-1));
	}

	@Test
	void applyUpper() {
		assertThrows(IndexException.class, () -> createList().apply(1));
	}

	@Test
	void apply() throws IndexException {
		var list = createList();
		var value = list.apply(0);
		assertEquals("test", value);
	}

	private List<String> createList() {
		return of(Strings::compareTo, "test");
	}

	@Test
	void size() {
		assertEquals(1, createList().size());
	}

	@Test
	void contains() {
		assertTrue(createList().contains("test"));
	}

	@Test
	void indexOf() {
		assertEquals(0, createList().indexOf("test"));
	}

	@Test
	void set() throws IndexException {
		assertEquals("test1", createList().set(0, "test1").apply(0));
	}

	@Test
	void setPadded() {
		assertArrayEquals(new Object[]{"test", null, null, "test1"}, createList()
				.set(3, "test1")
				.asArray());
	}

	@Test
	void remove() {
		assertArrayEquals(new Object[0], createList().remove("test").asArray());
	}

	@Test
	void asArray() {
		assertArrayEquals(new Object[]{"test"}, createList().asArray());
	}

	@Test
	void add() {
		assertArrayEquals(new Object[]{"test", "test1"}, createList()
				.add("test1")
				.asArray());
	}
}