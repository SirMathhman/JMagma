package com.meti.api.collect.stream;

import com.meti.api.collect.list.MutableList;
import com.meti.api.collect.stream.ListStream;
import com.meti.api.collect.stream.StreamException;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.list.ArrayList.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ListStreamTest {

	@Test
	void anyMatchValid() {
		assertTrue(ListStream.ListStreams.of("test0", "test1").anyMatch("test1"::equals));
	}

	@Test
	void anyMatchInvalid() {
		assertFalse(ListStream.ListStreams.of("test0", "test1").anyMatch("test"::equals));
	}

	@Test
	void filter() throws StreamException {
		var list = ListStream.ListStreams.of(0, 1, 2)
				.filter(value -> value % 2 == 0)
				.fold(ArrayList(), MutableList::add);
		assertEquals(ArrayList(0, 2), list);
	}

	@Test
	void map() throws StreamException {
		assertEquals("012", ListStream.ListStreams.of(0, 1, 2)
				.map(String::valueOf)
				.fold("", (value0, value1) -> value0 + value1));
	}

	@Test
	void mapExceptionally() throws StreamException {
		assertEquals("012", ListStream.ListStreams.of(0, 1, 2)
				.mapExceptionally(String::valueOf)
				.fold("", (value0, value1) -> value0 + value1));
	}

	@Test
	void foldExceptionally() throws StreamException {
		var list = ListStream.ListStreams.of("test0", "test1")
				.foldExceptionally(ArrayList(), MutableList::add);
		assertEquals(ArrayList("test0", "test1"), list);
	}

	@Test
	void fold() {
		var list = ListStream.ListStreams.of("test0", "test1")
				.fold(ArrayList(), MutableList::add);
		assertEquals(ArrayList("test0", "test1"), list);
	}

	@Test
	void head() {
		var head = ListStream.ListStreams.of("test0", "test1").head().orElse("other");
		assertEquals("test0", head);
	}
}