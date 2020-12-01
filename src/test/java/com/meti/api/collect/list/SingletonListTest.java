package com.meti.api.collect.list;

import com.meti.api.collect.IndexException;
import com.meti.api.collect.list.List;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.list.SingletonList.SingletonList;
import static org.junit.jupiter.api.Assertions.*;

class SingletonListTest {

	@Test
	void size() {
		assertEquals(1, createList().size());
	}

	@Test
	void getValid() throws IndexException {
		assertEquals("test", createList().get(0));
	}

	@Test
	void getInvalid() {
		assertThrows(IndexException.class, () -> createList().get(-1));
	}

	@Test
	void isEmpty() {
		assertFalse(createList().isEmpty());
	}

	private List<String> createList() {
		return SingletonList("test");
	}

	@Test
	void testEquals() {
		assertEquals(createList(), createList());
	}
}