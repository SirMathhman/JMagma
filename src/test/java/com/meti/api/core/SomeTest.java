package com.meti.api.core;

import org.junit.jupiter.api.Test;

import static com.meti.api.core.Some.Some;
import static org.junit.jupiter.api.Assertions.*;

class SomeTest {

	@Test
	void map() {
		assertEquals(4, Some("test").map(String::length).orElse(-1));
	}

	@Test
	void mapExceptionally() {
		assertEquals(4, Some("test").mapExceptionally(String::length).orElse(-1));
	}

	@Test
	void orElse() {
		assertEquals("test", Some("test").orElse(""));
	}

	@Test
	void filter() {
		assertTrue(Some("test").filter(value -> value.startsWith("m")).isEmpty());
	}

	@Test
	void isPresent() {
		assertTrue(Some("test").isPresent());
	}

	@Test
	void orElseThrow() {
		assertDoesNotThrow(() -> Some("test").orElseThrow(Exception::new));
	}

	@Test
	void isEmpty() {
		assertFalse(Some("test").isEmpty());
	}
}