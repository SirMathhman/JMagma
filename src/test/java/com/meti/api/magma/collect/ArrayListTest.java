package com.meti.api.magma.collect;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {A

	@Test
	void apply() throws IndexException {
		var expected = "test";
		var list = new ArrayList<>(new Object[]{expected}, 1);
		var actual = list.apply(0);
		assertEquals(expected, actual);
	}

	@Test
	void applyNegative() throws IndexException {
		var list = new ArrayList<>(new Object[0], 1);
		assertThrows(IndexException.class, () -> list.apply(-1));
	}

	@Test
	void applyPositive() {
		var list = new ArrayList<>(new Object[0], 0);
		assertThrows(IndexException.class, () -> list.apply(0));
	}

	@Test
	void set() throws IndexException {
		var list = new ArrayList<>(new Object[1], 0).set(0, "test");
		assertEquals("test", list.apply(0));
		assertEquals(1, list.size());
	}

	@Test
	void setNegative() throws IndexException {
		var list = new ArrayList<>(new Object[0], 0);
		assertThrows(IndexException.class, () -> list.set(-1, "test"));
	}

	@Test
	void setPositive() throws IndexException {
		var list = new ArrayList<>(new Object[0], 0).set(0, "test");
		assertEquals("test", list.apply(0));
		assertEquals(1, list.size());
	}

	@Test
	void setResizing() throws IndexException {
		var list = new ArrayList<>(new Object[0], 0).set(4, "test");
		assertEquals("test", list.apply(4));
		assertEquals(5, list.size());
	}

	@Test
	void size() {
		var expected = 420;
		var list = new ArrayList<>(new Object[0], expected);
		var actual = list.size();
		assertEquals(expected, actual);
	}
}