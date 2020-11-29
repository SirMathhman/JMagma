package com.meti;

import org.junit.jupiter.api.Test;

import static com.meti.ListStream.ListStream;
import static com.meti.api.collect.ArrayList.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ListStreamTest {

	@Test
	void anyMatchValid() {
		assertTrue(ListStream("test0", "test1").anyMatch("test1"::equals));
	}

	@Test
	void anyMatchInvalid() {
		assertFalse(ListStream("test0", "test1").anyMatch("test"::equals));
	}

	@Test
	void filter() {
		var list = ListStream(0, 1, 2)
				.filter(value -> value % 2 == 0)
				.fold(ArrayList(), MutableList::add);
		assertEquals(ArrayList(0, 2), list);
	}

	@Test
	void map() throws StreamException {
		assertEquals("012", ListStream(0, 1, 2)
				.map(String::valueOf)
				.fold("", (value0, value1) -> value0 + value1));
	}

	@Test
	void mapExceptionally() throws StreamException {
		assertEquals("012", ListStream(0, 1, 2)
				.mapExceptionally(String::valueOf)
				.fold("", (value0, value1) -> value0 + value1));
	}

	@Test
	void foldExceptionally() throws StreamException {
		var list = ListStream("test0", "test1")
				.foldExceptionally(ArrayList(), MutableList::add);
		assertEquals(ArrayList("test0", "test1"), list);
	}

	@Test
	void fold() {
		var list = ListStream("test0", "test1")
				.fold(ArrayList(), MutableList::add);
		assertEquals(ArrayList("test0", "test1"), list);
	}

	@Test
	void head() {
		var head = ListStream("test0", "test1").head().orElse("other");
		assertEquals("test0", head);
	}
}