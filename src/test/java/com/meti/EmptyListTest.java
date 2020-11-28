package com.meti;

import org.junit.jupiter.api.Test;

import static com.meti.EmptyList.EmptyList;
import static org.junit.jupiter.api.Assertions.*;

class EmptyListTest {
	@Test
	void size() {
		assertEquals(0, EmptyList().size());
	}

	@Test
	void get() {
		assertThrows(IndexException.class, () -> EmptyList().get(0));
	}

	@Test
	void isEmpty() {
		assertTrue(EmptyList().isEmpty());
	}
}