package com.meti;

import org.junit.jupiter.api.Test;

import static com.meti.None.None;
import static org.junit.jupiter.api.Assertions.*;

class NoneTest {

	@Test
	void map() {
		assertTrue(None().map(value -> value).isEmpty());
	}

	@Test
	void mapExceptionally() {
		assertTrue(None().mapExceptionally(value -> value).isEmpty());
	}

	@Test
	void orElse() {
		assertEquals("test1", None().orElse("test1"));
	}

	@Test
	void filter() {
		assertTrue(None().filter(value -> true).isEmpty());
	}

	@Test
	void isPresent() {
		assertFalse(None().isPresent());
	}

	@Test
	void orElseThrow() {
		assertThrows(IndexException.class, () -> None().orElseThrow(IndexException::new));
	}

	@Test
	void isEmpty() {
		assertTrue(None().isEmpty());
	}
}