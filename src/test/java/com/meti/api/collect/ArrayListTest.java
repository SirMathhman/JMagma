package com.meti.api.collect;

import org.junit.jupiter.api.Test;

import static com.meti.api.collect.ArrayList.*;
import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

	@Test
	void toArray() {
		Integer[] expected = {1};
		Object[] actual = ArrayList(expected).toArray();
		assertArrayEquals(expected, actual);
	}

	@Test
	void setInBounds() throws IndexException {
		assertArrayEquals(new Object[]{"test"}, ArrayList("dummy")
				.set(0, "test")
				.toArray());
	}

	@Test
	void setOutBounds() throws IndexException {
		Object[] array = ArrayList("test0")
				.set(2, "test2")
				.toArray();
		assertArrayEquals(new Object[]{"test0", null, "test2"}, array);
	}

	@Test
	void add() {
		Object[] array = ArrayList("test0")
				.add("test2")
				.toArray();
		assertArrayEquals(new Object[]{"test0", "test2"}, array);
	}

	@Test
	void asEmpty() {
		Object[] array = ArrayList("test0")
				.asEmpty()
				.toArray();
		assertEquals(0, array.length);
	}

	@Test
	void size() {
		assertEquals(3, ArrayList("test0", "test1", "test2").size());
	}

	@Test
	void get() throws IndexException {
		assertEquals("test1", ArrayList("test0", "test1", "test2").get(1));
	}
}